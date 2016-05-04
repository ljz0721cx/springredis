package com.janle.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringRedisDemoTest {
	@Autowired
	private RedisTemplate jedisTemplate;

	@Test
	public void testJedis() {
		try {
			 RedisSerializer<String> serializer=jedisTemplate.getStringSerializer();
			 byte[] key = serializer.serialize("Janle");
			System.out.println(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
