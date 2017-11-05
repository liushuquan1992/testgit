package com.lsq.autoconf;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.exceptions.JedisException;

@Component
public class JedisOperator {
	
	private static Logger logger = LoggerFactory.getLogger(JedisOperator.class);
	
	@Autowired
    private JedisPool jedisPool;
	
	private static Pipeline pipeline = null;

    public Jedis getResource() {
        return jedisPool.getResource();
    }
    
    @SuppressWarnings("deprecation")
	public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResourceObject(jedis);
        }
    }
    
    public void remove (String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.expire(key, 0);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis remove error: " + e.getMessage() + " - " + key );
        } finally {
            returnResource(jedis);
        }
    }
    
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(key, value);
            logger.debug("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + value);
        } finally {
            returnResource(jedis);
        }
    }
	
    public void setex(String key, String value, Integer seconds) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.setex(key, seconds, value);
            logger.debug("Redis set success - " + key + ", time = " + seconds + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: " + e.getMessage() + " - " + key + ", time = " + seconds + ", value:" + value);
        } finally {
            returnResource(jedis);
        }
    }
    
    public String get(String key) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.get(key);
            logger.debug("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + result);
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    
    public String getString(String key) {
        String result = "";
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.get(key);
            if(result==null){
            	return ""; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + result);
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    
    /**
	 * 由jedis代理，向redis缓存放数据
	 * 
	 * @param key
	 * @param value
	 * @throws JedisException
	 */
	public void HSET(String key, String field, String value){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.hset(key, field, value);
		} catch (JedisException e) {
			logger.error("Redis HSET has error：", e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 删除哈希表 key中的一个或多个指定域。
	 * @param key
	 * @param field
	 */
	public void HDEL(String key , String ...field) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.hdel(key, field);
		} catch (JedisException e) {
			logger.error("Redis HDEL has error：", e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	public Map<String, String> HGETALL(String key) {
		Map<String, String> map = null;
		Jedis jedis = null;
		try {
			jedis = getResource() ;
			pipeline = jedis.pipelined() ;
			Response<Map<String, String>> response = pipeline.hgetAll(key);
			pipeline.sync();
			map = response.get();
		}
		catch (Exception e) {
			logger.error("从Jedis获取HGETALL值出现异常：", e);
		}finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return map;
	}
}
