package org.moana.redis;

import org.redisson.Config;
import org.redisson.Redisson;
/**
 * @TODO：
 * @fileName : org.moana.redis.RedissionFactory.java
 *  author | version |   
 *  Jiong | 1.0 |
 */
public class RedissonFactory {
	private static Redisson redisson;
	
	public static Redisson getRedisson(){
		if(redisson==null){
			Config config = new Config();
			config.setConnectionPoolSize(10);  
		    config.addAddress(org.moana.util.Config.REDIS_ADDRESS);  
		    redisson = Redisson.create(config);
		}
		return redisson;
	}
	
	
}

