package com.janle.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

public class RedisZsetTest {
	private Jedis jedis;
	private String key = "zset_key";

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
	 * 该命令添加指定的成员到key对应的有序集合中，每个成员都有一个分数。你可以指定多个分数/成员组合。如果一个指定的成员已经在对应的有序集合中了，
	 * 那么其分数就会被更新成最新的
	 * ，并且该成员会重新调整到正确的位置，以确保集合有序。如果key不存在，就会创建一个含有这些成员的有序集合，就好像往一个空的集合中添加一样
	 * 。如果key存在，但是它并不是一个有序集合，那么就返回一个错误。
	 * 
	 * 分数的值必须是一个表示数字的字符串，并且可以是double类型的浮点数。
	 *
	 * @param key
	 * @param score
	 * @param member
	 * @return 返回添加到有序集合中元素的个数，不包括那种已经存在只是更新分数的元素。
	 */
	@Test
	public void Testzadd() {
		Long count = jedis.zadd(key, 0.5, "zset_01");// 返回添加的状态
		System.out.println(count);
	}

	/**
	 * 该命令添加指定的成员到key对应的有序集合中，每个成员都有一个分数。你可以指定多个分数/成员组合。如果一个指定的成员已经在对应的有序集合中了，
	 * 那么其分数就会被更新成最新的
	 * ，并且该成员会重新调整到正确的位置，以确保集合有序。如果key不存在，就会创建一个含有这些成员的有序集合，就好像往一个空的集合中添加一样
	 * 。如果key存在，但是它并不是一个有序集合，那么就返回一个错误。
	 * 
	 * 分数的值必须是一个表示数字的字符串，并且可以是double类型的浮点数。
	 *
	 * @param key
	 * @param scoreMembers
	 * @return 返回添加到有序集合中元素的个数，不包括那种已经存在只是更新分数的元素。
	 */
	@Test
	public void TestzaddMap() {
		// 如果再次提交将会覆盖double值
		Map<String, Double> scoreMembers = new HashMap<String, Double>();
		scoreMembers.put("zset_02", 0.10);
		scoreMembers.put("zset_03", 0.7);
		Long sets = jedis.zadd(key, scoreMembers);
		System.out.println(sets);
	}

	/**
	 * 返回有序集key中，指定区间内的成员。其中成员按score值递增(从小到大)来排序。具有相同score值的成员按字典序来排列。
	 * 
	 * 如果你需要成员按score值递减(score相等时按字典序递减)来排列，请使用ZREVRANGE命令。
	 * 下标参数start和stop都以0为底，也就是说，以0表示有序集第一个成员，以1表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以-1表示最后一个成员，-2表示倒数第二个成员，以此类推。
	 * 
	 * 超出范围的下标并不会引起错误。如果start的值比有序集的最大下标还要大，或是start >
	 * stop时，ZRANGE命令只是简单地返回一个空列表。
	 * 另一方面，假如stop参数的值比有序集的最大下标还要大，那么Redis将stop当作最大下标来处理。
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return 指定范围的元素列表
	 */
	@Test
	public void Testzrange() {
		Set<String> sets = jedis.zrange(key, 0, 10);
		System.out.println(sets);
	}

	/**
	 * 从集合中删除指定member元素，当key存在，但是其不是有序集合类型，就返回一个错误。
	 *
	 * @param key
	 * @param member
	 * @return 返回的是从有序集合中删除的成员个数，不包括不存在的成员。
	 */
	@Test
	public void Testzrem() {
		Long sets = jedis.zrem(key, new String[] { "zset_02" });
		System.out.println(sets);
	}

	/**
	 * 为有序集key的成员member的score值加上增量increment。如果key中不存在member，就在key中添加一个member，
	 * score是increment（就好像它之前的score是0.0）。如果key不存在，就创建一个只含有指定member成员的有序集合。
	 * 
	 * 当key不是有序集类型时，返回一个错误。
	 * 
	 * score值必须整数值或双精度浮点数。也有可能给一个负数来减少score的值。
	 *
	 * @param key
	 * @param score
	 * @param member
	 * @return member成员的新score值.
	 */
	@Test
	public void Testzincrby() {
		Double zincrby = jedis.zincrby(key, 1, "zset_02");// 如果没有值将会自己创建一个
		System.out.println(zincrby);
	}

	/**
	 * 返回有序集key中成员member的排名。其中有序集成员按score值递增(从小到大)顺序排列。排名以0为底，也就是说，
	 * score值最小的成员排名为0。
	 * 
	 * 使用ZREVRANK命令可以获得成员按score值递减(从大到小)排列的排名。
	 *
	 * @param key
	 * @param member
	 * @return 如果member是有序集key的成员，返回member的排名的整数。 如果member不是有序集key的成员，返回 nil。
	 */
	@Test
	public void Testzrank() {
		Long sets = jedis.zrank(key, "zset_01"); // 0，1，2
		System.out.println(sets);
	}

	/**
	 * 返回有序集key中成员member的排名，其中有序集成员按score值从大到小排列。排名以0为底，也就是说，score值最大的成员排名为0。
	 * 
	 * 使用ZRANK命令可以获得成员按score值递增(从小到大)排列的排名。
	 *
	 * @param key
	 * @param member
	 * @return 如果member是有序集key的成员，返回member的排名。整型数字。 如果member不是有序集key的成员，返回Bulk
	 *         reply: nil.
	 */
	@Test
	public void Testzrevrank() {
		Long sets = jedis.zrevrank(key, "zset_01"); // 上边的double排序的反序
		System.out.println(sets);
	}

	/**
	 * 返回有序集key中，指定区间内的成员。其中成员的位置按score值递减(从大到小)来排列。具有相同score值的成员按字典序的反序排列。
	 * 除了成员按score值递减的次序排列这一点外，ZREVRANGE命令的其他方面和ZRANGE命令一样。
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return 指定范围的元素列表(可选是否含有分数)。
	 */
	@Test
	public void Testzrevrange() {
		Set<String> zrevrange = jedis.zrevrange(key, 0, -1);
		System.out.println(zrevrange);
	}

	/**
	 * 返回有序集key中，指定区间内的成员。其中成员按score值递增(从小到大)来排序。具有相同score值的成员按字典序来排列。
	 * 
	 * 如果你需要成员按score值递减(score相等时按字典序递减)来排列，请使用ZREVRANGE命令。
	 * 下标参数start和stop都以0为底，也就是说，以0表示有序集第一个成员，以1表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以-1表示最后一个成员，-2表示倒数第二个成员，以此类推。
	 * 
	 * 超出范围的下标并不会引起错误。如果start的值比有序集的最大下标还要大，或是start >
	 * stop时，ZRANGE命令只是简单地返回一个空列表。
	 * 另一方面，假如stop参数的值比有序集的最大下标还要大，那么Redis将stop当作最大下标来处理。
	 * 
	 * 使用WITHSCORES选项，来让成员和它的score值一并返回，返回列表以value1,score1, ...,
	 * valueN,scoreN的格式表示，而不是value1,...,valueN。客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return 指定范围的元素列表(以元组集合的形式)。
	 */
	@Test
	public void TestzrangeWithScores() {
		Set<Tuple> sets = jedis.zrangeWithScores(key, 0, 1);
		for (Tuple tuple : sets) {
			System.out.println(tuple.getElement() + " " + tuple.getScore());
		}
	}

	/**
	 * 返回有序集key中，指定区间内的成员。其中成员的位置按score值递减(从大到小)来排列。具有相同score值的成员按字典序的反序排列。
	 * 除了成员按score值递减的次序排列这一点外，ZREVRANGE命令的其他方面和ZRANGE命令一样。
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return 指定范围的元素列表(可选是否含有分数)。
	 */
	@Test
	public void TestzrevrangeWithScores() {
		Set<Tuple> sets = jedis.zrevrangeWithScores(key, 0, 1);
		for (Tuple tuple : sets) {
			System.out.println(tuple.getElement() + " " + tuple.getScore());
		}
	}

	/**
	 * 返回key的有序集元素个数。
	 *
	 * @param key
	 * @return key存在的时候，返回有序集的元素个数，否则返回0。
	 */
	@Test
	public void Testzcard() {
		Long sets = jedis.zcard(key);
		System.out.println(sets);
	}

	/**
	 * 返回有序集key中，成员member的score值。
	 * 
	 * 如果member元素不是有序集key的成员，或key不存在，返回nil。
	 *
	 * @param key
	 * @param member
	 * @return member成员的score值（double型浮点数）
	 */
	@Test
	public void Testzscore() {
		Double dou = jedis.zscore(key, "zset_02");
		System.out.println(dou);
	}
}
