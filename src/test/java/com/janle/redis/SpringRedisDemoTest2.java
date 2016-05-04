package com.janle.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.janle.redis.dao.UserDao;
import com.janle.redis.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringRedisDemoTest2 {
	@Autowired
	private UserDao userDao;
	private User user;

	@Before
	public void init() {
		user = new User();
		user.setId("Janle");
		user.setName("李建");
	}

	@Ignore
	@Test
	public void testAddUser() {
		boolean result = userDao.add(user);
		Assert.assertTrue(result);
	}

	/**
	 * 普通方式 批量新增和pipeline不是一个量级
	 * 
	 * 普通：2439 pipeline 278
	 */
	// @Ignore
	@Test
	public void testAddUsers1() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 5000; i++) {
			User user = new User();
			user.setId("user_" + i);
			user.setName("Janle_" + i);
			list.add(user);
		}
		long begin = System.currentTimeMillis();
		for (User user1 : list) {
			userDao.add(user1);
		}
		System.out.println(System.currentTimeMillis() - begin);
	}

	/**
	 * 批量新增 pipeline方式
	 */
	@Ignore
	@Test
	public void testAddUsers2() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 5000; i++) {
			User user = new User();
			user.setId("user_" + i);
			user.setName("Janle_" + i);
			list.add(user);
		}
		long begin = System.currentTimeMillis();
		userDao.add(list);
		System.out.println(System.currentTimeMillis() - begin);
	}

	@Ignore
	@Test
	public void delete() {
		userDao.delete("Janle2");
	}

	@Ignore
	@Test
	public void deleteList() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 5000; i++) {
			list.add("user_" + i);
		}
		userDao.delete(list);
	}

	// @Ignore
	@Test
	public void get() {
		for (int i = 0; i < 5000; i++) {
			User user1 = userDao.get("user_" + i);
			System.out.println(user1);
		}

	}
}
