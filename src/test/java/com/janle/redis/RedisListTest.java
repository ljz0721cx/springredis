package com.janle.redis;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisListTest {
	private Jedis jedis;
	private String key = "list_key";

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
	 * 向存于 key 的列表的尾部插入所有指定的值。如果 key 不存在，那么会创建一个空的列表然后再进行 push 操作。 当 key
	 * 保存的不是一个列表，那么会返回一个错误。
	 * 
	 * 可以使用一个命令把多个元素打入队列，只需要在命令后面指定多个参数。元素是从左到右一个接一个从列表尾部插入。 比如命令 RPUSH mylist a
	 * b c 会返回一个列表，其第一个元素是 a ，第二个元素是 b ，第三个元素是 c。 2.4以后的版本支持多个值
	 * 
	 * @param key
	 * @param string
	 * @return 在 push 操作后的列表长度。
	 */
	@Test
	public void Testrpush() {
		Long result;
		try {
			result = jedis.rpush(key, new String[] { "key_a" });
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 将所有指定的值插入到存于 key 的列表的头部。如果 key 不存在，那么在进行 push 操作前会创建一个空列表。 如果 key
	 * 对应的值不是一个 list 的话，那么会返回一个错误。
	 * 
	 * 可以使用一个命令把多个元素 push 进入列表，只需在命令末尾加上多个指定的参数。元素是从最左端的到最右端的、一个接一个被插入到 list
	 * 的头部。 所以对于这个命令例子 LPUSH mylist a b c，返回的列表是 c 为第一个元素， b 为第二个元素， a 为第三个元素。
	 *
	 * @param key
	 * @param string
	 * @return 在 push 操作后的列表长度。
	 */
	@Test
	public void Testlpush() {
		Long result;
		try {
			result = jedis.lpush(key, new String[] { "key_3"});
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 /**
     * 返回存储在 key 里的list的长度。 如果 key 不存在，那么就被看作是空list，并且返回长度为 0。 当存储在 key
     * 里的值不是一个list的话，会返回error。
     *
     * @param key
     * @return key对应的list的长度。
     */
	@Test
	public void Testllen() {
		Long result;
		try {
			result = jedis.llen(key);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
     * 返回存储在 key 的列表里指定范围内的元素。 start 和 end
     * 偏移量都是基于0的下标，即list的第一个元素下标是0（list的表头），第二个元素下标是1，以此类推。
     * 
     * 偏移量也可以是负数，表示偏移量是从list尾部开始计数。 例如， -1 表示列表的最后一个元素，-2 是倒数第二个，以此类推。
     *
     * @param key
     * @param start
     * @param end
     * @return 指定范围里的列表元素。
     */
	@Test
	public void Testlrange(){
		List<String> list=jedis.lrange(key, 0, -1);
		System.out.println(list);
	}
    
	 /**
     * 修剪(trim)一个已存在的 list，这样 list 就会只包含指定范围的指定元素。start 和 stop 都是由0开始计数的， 这里的 0
     * 是列表里的第一个元素（表头），1 是第二个元素，以此类推。
     * [key_3, key_z, key_z, key_a] 0，1
     * [key_3, key_z] 截取列表保留截取后的列表
     * @param key
     * @param start
     * @param end
     * @return
     */
	@Test
	public void Testltrim(){
		String aa=jedis.ltrim(key, 0, 1);
		System.out.println(aa);
	}
	/**
     * 返回列表里的元素的索引 index 存储在 key 里面。 下标是从0开始索引的，所以 0 是表示第一个元素， 1 表示第二个元素，并以此类推。
     * 负数索引用于指定从列表尾部开始索引的元素。在这种方法下，-1 表示最后一个元素，-2 表示倒数第二个元素，并以此往前推。
     * 
     * 当 key 位置的值不是一个列表的时候，会返回一个error。
     *
     * @param key
     * @param index
     * @return 请求的对应元素，或者当 index 超过范围的时候返回 nil。
     */
	@Test
	public void Testlindex(){
		String index=jedis.lindex(key, 1);//返回查询的结果
		System.out.println(index);
	}
	
	 /**
     * 设置 index 位置的list元素的值为 value。
     * 
     * 当index超出范围时会返回一个error。
     *
     * @param key
     * @param index
     * @param value
     * @return 状态恢复
     */
	@Test
	public void Testlset(){
		//位置0替换value
		String set=jedis.lset(key, 0, "set_key");
		System.out.println(set);
	}

    /**
     * 从存于 key 的列表里移除前 count 次出现的值为 value 的元素。 这个 count 参数通过下面几种方式影响这个操作：
     * 
     * count > 0: 从头往尾移除值为 value 的元素。 count < 0: 从尾往头移除值为 value 的元素。 count = 0:
     * 移除所有值为 value 的元素。
     * 
     * 比如， LREM list -2 "hello" 会从存于 list 的列表里移除最后两个出现的 "hello"。
     * 
     * 需要注意的是，如果list里没有存在key就会被当作空list处理，所以当 key 不存在的时候，这个命令会返回 0。
     *
     * @param key
     * @param count
     * @param value
     * @return 返回删除的个数
     */
	@Test
	public void Testlrem(){
		Long lremCount= jedis.lrem(key, 4, "key_z");
		System.out.println(lremCount);
	}

    /**
     * 移除并且返回 key 对应的 list 的第一个元素。
     *
     * @param key
     * @return 返回第一个元素的值，或者当 key 不存在时返回 nil。
     */
	@Test
	public void Testlpop(){
		//弹出首元素
		String lpop= jedis.lpop(key);
		System.out.println(lpop);
	}
    /**
     * 移除并返回存于 key 的 list 的最后一个元素。
     *
     * @param key
     * @return 最后一个元素的值，或者当 key 不存在的时候返回 nil。
     */
	@Test
	public void Testrpop(){
		//弹出最后一个元素
		String rpop= jedis.rpop(key);
		System.out.println(rpop);
	}
    
}
