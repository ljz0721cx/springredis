package com.janle.redis;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisSetTest {
	private Jedis jedis;
	private String key = "set_key";

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
	 * 添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略.如果集合key
	 * 不存在，则新建集合key,并添加member元素到集合key中.
	 * 
	 * 如果key 的类型不是集合则返回错误.
	 *
	 * @param key
	 * @param member
	 * @return 返回新成功添加到集合里元素的数量，不包括已经存在于集合中的元素.
	 */
	@Test
	public void Testsadd() {
		// 如果已经存在将会忽略
		Long set = jedis.sadd(key, new String[] { "set", "sete", "setaaa" });
		System.out.println(set);
	}

	/**
	 * 返回key集合所有的元素.
	 * 
	 * 该命令的作用与使用一个参数的SINTER 命令作用相同.
	 *
	 * @param key
	 * @return 集合中的所有元素.
	 */
	@Test
	public void Testsmembers() {
		Set<String> sets = jedis.smembers(key);
		System.out.println(sets);
	}

	/**
	 * 在key集合中移除指定的元素. 如果指定的元素不是key集合中的元素则忽略 如果key集合不存在则被视为一个空的集合，该命令返回0.
	 * 
	 * 如果key的类型不是一个集合,则返回错误.
	 *
	 * @param key
	 * @param member
	 * @return 从集合中移除元素的个数，不包括不存在的成员.
	 */
	@Test
	public void Testsrem() {
		Long rcount = jedis.srem(key, "set_1");
		System.out.println(rcount);
	}

	/**
	 * 移除并返回一个集合中的随机元素
	 * 
	 * 该命令与 SRANDMEMBER相似,不同的是srandmember命令返回一个随机元素但是不移除.
	 *
	 * @param key
	 * @return 被移除的元素, 当key不存在的时候返回 nil .
	 */
	@Test
	public void Testspop() {
		String popSet = jedis.spop(key);
		System.out.println(popSet);
	}

	/**
	 * 移除并返回多个集合中的随机元素
	 * 
	 * @param key
	 * @param count
	 * @return 被移除的元素, 当key不存在的时候值为 nil .
	 */
	@Test
	public void Testspop1() {
		/*
		 * Set<String> sets=jedis.spop(key,1L); System.out.println(sets);
		 */
	}

	/**
	 * 返回集合存储的key的基数 (集合元素的数量).
	 *
	 * @param key
	 * @return 集合的基数(元素的数量),如果key不存在,则返回 0.
	 */
	@Test
	public void Testscard() {
		Long sets = jedis.scard(key);
		System.out.println(sets);
	}

	/**
	 * 返回成员 member 是否是存储的集合 key的成员.
	 *
	 * @param key
	 * @param member
	 * @return 如果member元素是集合key的成员，则返回1.如果member元素不是key的成员，或者集合key不存在，则返回0
	 */
	@Test
	public void Testsismember() {
		Boolean sets = jedis.sismember(key, "set_1");
		System.out.println(sets);
	}

	/**
	 * 仅提供key参数,那么随机返回key集合中的一个元素.该命令作用类似于SPOP命令, 不同的是SPOP命令会将被选择的随机元素从集合中移除,
	 * 而SRANDMEMBER仅仅是返回该随记元素,而不做任何操作.
	 *
	 * @param key
	 * @return 返回随机的元素,如果key不存在则返回nil
	 */
	@Test
	public void Testsrandmember() {
		String sets = jedis.srandmember(key);
		System.out.println(sets);
	}

}
