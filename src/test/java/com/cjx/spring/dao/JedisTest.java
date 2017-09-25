package com.cjx.spring.dao;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

    /**
     * 单实例的测试
     */
    @Test
    public void testJedis1() {

        /**
         *  当报错：java.net.SocketTimeoutException: connect timed out
         *  是 CentOS 没有打开 6379 端口 及 其他一些问题
         *  1.vim /etc/sysconfig/iptables     打开 6379 端口
         *  2.保存后重启防火墙 service iptables restart
         *  3.需要注释掉 redis.conf 文件中的 bind 127.0.0.1 ；并设置 protected-mode 为 no
         *  4.重启 redis
         */

        //1.设置 IP 地址和端口
        Jedis jedis = new Jedis("192.168.1.111", 6379);
        //2.保存数据
        jedis.set("name", "cjx");
        //3.获取数据
        String name = jedis.get("name");
        System.out.println(name);
        //4.释放资源
        jedis.close();
    }


    /**
     * 连接池的方式
     */
    @Test
    public void testJedis2() {
        //获得连接池的配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(30);
        //设置最大的空闲连接数
        jedisPoolConfig.setMaxIdle(10);

        //获得连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.1.111", 6379);

        //获得核心对象
        Jedis jedis = null;
        try {
            //通过连接池来获得连接
            jedis = jedisPool.getResource();
            //保存数据
            jedis.set("today", "2017年9月22日");
            //获取数据
            String today = jedis.get("today");
            System.out.println(today);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null){
                jedisPool.close();
            }
        }
    }
}
