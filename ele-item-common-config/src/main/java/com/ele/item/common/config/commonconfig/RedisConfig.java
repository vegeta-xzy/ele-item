package com.ele.item.common.config.commonconfig;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource(value= {"classpath:${spring.profiles.include}/application-common-dev-redis.properties"})
@ConditionalOnClass({JedisCluster.class})
public class RedisConfig {

	@Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;
//    @Value("${spring.redis.commandTimeout}")
//    private int commandTimeout;
    @Value("${spring.redis.cluster.max-redirects}")
    private int maxRedirects;
    @Value("${spring.redis.password}")
    private String password;
    
    
    @Bean
    public JedisCluster getJedisCluster() {
        String[] cNodes = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        //分割出集群节点
        for (String node : cNodes) {
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(60000);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(true);
        
//    	JedisPoolConfig config = new JedisPoolConfig();
//		config =new JedisPoolConfig();
//        config.setMaxTotal(60000);//设置最大连接数  
//        config.setMaxIdle(1000); //设置最大空闲数 
//        config.setMaxWaitMillis(3000);//设置超时时间  
//        config.setTestOnBorrow(true);
//		
//		
//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7001")));
//		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7002")));
//		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7003")));
//		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7004")));
//		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7005")));
//		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7006")));

//		JedisCluster jc = new JedisCluster(nodes, 5000, 5000,100, "redis504", config);
        
        //创建集群对象
//      JedisCluster jedisCluster = new JedisCluster(nodes,commandTimeout);
//        return new JedisCluster(nodes, timeout, jedisPoolConfig);
        return new JedisCluster(nodes, timeout, timeout,100, password, jedisPoolConfig);
//		return jc;
    }
    
    /**
     * Redis集群的配置
     * @return RedisClusterConfiguration
     * @throws
     */
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration(){
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        //Set<RedisNode> clusterNodes
//        String[] serverArray = clusterNodes.split(",");
//        Set<RedisNode> nodes = new HashSet<RedisNode>();
//        for(String ipPort:serverArray){
//            String[] ipAndPort = ipPort.split(":");
//            nodes.add(new RedisNode(ipAndPort[0].trim(),Integer.valueOf(ipAndPort[1])));
//        }
//        redisClusterConfiguration.setClusterNodes(nodes);
//        redisClusterConfiguration.setMaxRedirects(maxRedirects);
//        redisClusterConfiguration.setPassword(RedisPassword.of(password));
//        return redisClusterConfiguration;
//    }

    /**
     * 设置数据存入redis 的序列化方式
     * </br>redisTemplate序列化默认使用的jdkSerializeable,存储二进制字节码,导致key会出现乱码，所以自定义
     * 序列化类
     *
     * @paramredisConnectionFactory
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
