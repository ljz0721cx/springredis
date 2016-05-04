package com.janle.redis.dao;

import java.util.List;

import com.janle.redis.entity.User;

public interface UserDao {

	boolean add(User user);

	boolean add(List<User> users);

	void delete(String key);

	void delete(List<String> key);

	boolean update(User user);

	User get(String keyId);
}
