#自己发布安装readis
=======
## redis相关文档
http://www.runoob.com/redis/redis-install.html
```
==================================WINDOW 下安装=================================
启动redis服务端
 运行 redis-server.exe redis.conf 。redis.conf路径可以省略
启动redis客户端
运行redis-cli.exe -h localhost -p 6379 。
测试 输入设置键值对 set myKey abc
取出键值对 get myKey
```
```
==================================Linux 下安装=================================
$ wget http://download.redis.io/releases/redis-2.8.17.tar.gz
$ tar xzf redis-2.8.17.tar.gz
$ cd redis-2.8.17
$ make
make完后 redis-2.8.17目录下会出现编译后的redis服务程序redis-server,还有用于测试的客户端程序redis-cli,两个程序位于安装目录 src 目录下：
启动redis服务.
./redis-server redis.conf
启动redis服务进程后，就可以使用测试客户端程序redis-cli和redis服务交互了
```
===================================================================
Redis连接： redis-cli -h host -p port -a password

## Redis 数据类型
String，hash，set，List，zset
	String基本操作命令:SET ，get
	hash基本操作命令：HMSET，HGETALL
	List基本操作命令：lpush，lrange key 0 10
	Set基本操作命令：sadd key member，smembers
	zset基本操作：zadd	key score member ，ZRANGEBYSCORE key 0 1000

redis支持消息pub/sub 生产消费

redis事物：事物是一个单独的隔离操作
	开启事务：multi，执行：exec
	
## Redis 分区
	分区是分割数据到多个Redis实例的处理过程，因此每个实例只保存key的一个子集
	优势：通过利用多台计算机的和值构建更大的数据库
		通过多核和多台计算机，允许我们扩展计算能力，通过多台计算机和网络适配器，允许我们扩展网络带宽
	劣势：不支持多key操作
		涉及多个key的radis事物不能使用
		使用分区时候，数据处理比较复杂
		增加或删除容量也比较复杂。redis集群大多数支持在运行时增加、删除节点的透明数据平衡的能力，但是类似于客户端分区、代理等其他系统则不支持这项特性。然而，一种叫做presharding的技术对此是有帮助的。
	


## 命令文档参考
http://redisdoc.com/







## spring配置redis

http://docs.spring.io/spring-data/redis/docs/1.7.1.RELEASE/reference/html/
