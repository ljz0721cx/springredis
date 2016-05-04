package com.janle.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisHashTest {
	private Jedis jedis;

	/**
	 * 初始化连接
	 */
	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1");
		System.out.println("----------------" + jedis.ping());
	}

	/**
	 * 断开连接 <br>
	 * ------------------------------<br>
	 */
	@After
	public void after() {
		jedis.disconnect();
	}

	/**
	 * 设置hash表里指定散列字段的值为value。如果key不存在，则创建一个新的hash表
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 如果该字段已经存在，那么将会更新该字段的值，返回0.如果字段不存在，则新创建一个并且返回1.
	 */
	@Test
	public void testhset() {
		Long result = jedis.hset("h_key", "name", "h_key_value");
		System.out.println(result == 0 ? "字段已经存在" : "字段不存在");
	}

	/**
	 * 获取存储在指定的键散列字段的值 如果该key对应的值是一个Hash表，则返回对应字段的值。
	 * 如果不存在该字段，或者key不存在，则返回一个"nil"值。
	 *
	 * @param key
	 * @param field
	 * @return
	 */
	@Test
	public void testhget() {
		String result = jedis.hget("h_key", "name");
		System.out.println(result);
	}

	/**
	 * 当字段不存在时，才进行set。
	 *
	 * @param key
	 * @param field
	 * @param value
	 * @return 如果该字段已经存在，则返回0.若字段不存在，则创建后set，返回1.
	 */

	@Test
	public void testhsetnx() {
		Long result = jedis.hsetnx("h_key", "name", "h_key_value");
		System.out.println(result == 0 ? "字段已经存在" : "字段不存在");
	}

	/**
	 * 设置多个字段和值，如果字段存在，则覆盖。
	 *
	 * @param key
	 * @param hash
	 * @return 设置成功返回OK，设置不成功则返回EXCEPTION
	 */
	@Test
	public void Testhmset() {
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("name", "h_key_value");
		hash.put("name1", "h_key_value2");
		hash.put("name3", "33");
		String result = jedis.hmset("h_key", hash);
		System.out.println(result);
	}

	/**
	 * 在hash中获取多个字段的值，若字段不存在，则其值为nil。
	 *
	 * @param key
	 * @param fields
	 * @return 按顺序返回多个字段的值。
	 */
	@Test
	public void Testhmget() {
		String[] strs = new String[] { "name", "name1" };
		List<String> result = jedis.hmget("h_key", strs);
		System.out.println(result.toString());
	}

	/**
	 * 对hash中指定字段的值增加指定的值
	 *
	 * @param key
	 * @param field
	 * @param value
	 *            这里必须是数字格式
	 * @return 返回增加后的新值
	 */
	@Test
	public void TesthincrBy() {
		// 这里一定是数字类型
		Long result;
		try {
			result = jedis.hincrBy("h_key", "name3", 1L);
			System.out.println(result.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 判断hash中指定字段是否存在
	 *
	 * @param key
	 * @param field
	 * @return 若存在返回1，若不存在返回0
	 */
	@Test
	public void Testhexists() {
		System.out.println(jedis.hexists("h_key", "name3"));
	}

	/**
	 * 删除hash中指定字段
	 *
	 * @param key
	 * @param field
	 * @return 删除成功返回1， 删除不成功返回0
	 */
	@Test
	public void Testhdel() {
		System.out.println(jedis.hdel("h_key", "name3"));
	}

	/**
	 * 返回 key 指定的哈希集包含的字段的数量
	 *
	 * @param key
	 * @return 哈希集中字段的数量，当 key 指定的哈希集不存在时返回 0
	 */
	@Test
	public void Testhlen() {
		System.out.println(jedis.hlen("h_key"));
	}

	/**
	 * 返回 key 指定的哈希集中所有字段的名字。
	 *
	 * @param key
	 * @return 哈希集中的字段列表，当 key 指定的哈希集不存在时返回空列表。
	 */
	@Test
	public void Testhkeys() {
		Set<String> set;
		try {
			set = jedis.hkeys("h_key");
			System.out.println(set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回 key 指定的哈希集中所有字段的值。
	 *
	 * @param key
	 * @return 哈希集中的值的列表，当 key 指定的哈希集不存在时返回空列表。
	 */
	@Test
	public void Testhvals() {
		List<String> set;
		try {
			set = jedis.hvals("h_key");
			System.out.println(set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回 key 指定的哈希集中所有的字段和值
	 *
	 * @param key
	 * @return 返回 key 指定的哈希集中所有的字段和值,若key不存在返回空map。
	 */
	@Test
	public void hgetAll() {
		Map<String, String> map = jedis.hgetAll("h_key");
		System.out.println(map);
	}

}
