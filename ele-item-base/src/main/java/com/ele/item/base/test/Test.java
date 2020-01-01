package com.ele.item.base.test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class Test {
	
	/**
	 * 
	 * @Title: getFixedTread   
	 * @Description: TODO (FixedThread)   
	 * @param: @param i
	 * @param: @return      
	 * @return: Runnable      
	 * @throws
	 */
	public static Runnable getFixedThread(final int i) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("FixedThread i====>"+i);
			}
		};
	}
	
	/**
	 * 
	 * @Title: getCachedThread   
	 * @Description: TODO (CachedThread)   
	 * @param: @param i
	 * @param: @return      
	 * @return: Runnable      
	 * @throws
	 */
	public static Runnable getCachedThread(final int i) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("CachedThread i==>"+i);
			}
		};
	}
	
	/**
	 * 
	 * @Title: getSingleThread   
	 * @Description: TODO (SingleThread)   
	 * @param: @param i
	 * @param: @return      
	 * @return: Runnable      
	 * @throws
	 */
	public static Runnable getSingleThread(final int i) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	/**
	 * 
	 * @Title: getScheduledThread   
	 * @Description: TODO (scheduledTread)   
	 * @param: @param i      
	 * @return: void      
	 * @throws
	 */
	public static void getScheduledThread(final int i) {
		ScheduledExecutorService sce = Executors.newScheduledThreadPool(i);
		sce.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(4000);
					System.out.println(Thread.currentThread().getId() + "执行了");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
	}
	
	
	public static void testJedis() {
		//测试jedis 连结
		JedisPoolConfig config = new JedisPoolConfig();
		config =new JedisPoolConfig();
        config.setMaxTotal(60000);//设置最大连接数  
        config.setMaxIdle(1000); //设置最大空闲数 
        config.setMaxWaitMillis(3000);//设置超时时间  
        config.setTestOnBorrow(true);
		
		
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7001")));
		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7002")));
		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7003")));
		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7004")));
		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7005")));
		nodes.add(new HostAndPort("47.107.182.147", Integer.parseInt("7006")));

		JedisCluster jc = new JedisCluster(nodes, 5000, 5000,100, "redis504", config);
		jc.set("xuzy", "xuzy123");
		String value = jc.get("xuzy");
		System.out.println("value="+value);
		try {
			jc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static void main(String[] args) {
		//Fixed
//		ExecutorService eixedExecutorService = Executors.newFixedThreadPool(5);//创建固定量线程池，可控制最大并发数，超出的线程在队列中等待
//		for(int i=0;i<100;i++) {
//			eixedExecutorService.execute(getFixedThread(i));
//		}
//		eixedExecutorService.shutdown();
		
		//cached 为调用shutDown方法，60秒过后会自动释放资源
//		ExecutorService cachedExecutorService = Executors.newCachedThreadPool();//（无界限）创建可缓存线程池，如果线程池长度超过处理需求，灵活回收空闲线程，若无可回收，则创建新线程
//		for (int i = 0; i < 100; i++) {
//			cachedExecutorService.execute(getCachedThread(i));
//		}
		
		//scheduled
//		getScheduledThread(10);//创建一个定长线程池，支持定时及周期任务执行
		
		
		//single 在线程中没有任务时可执行，也不会释放资源。所需要shutdown（Fixed同理）
//		ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();//创建单线程化线程池，它只会用唯一的线程执行任务，保证所有任务按照顺序（FIFO,LIFO,优先级）执行
//		for (int i = 0; i < 10; i++) {
//			singleExecutorService.execute(getSingleThread(i));
//		}
//		singleExecutorService.shutdown();
		
		
//		Jedis jedis = new Jedis("47.107.182.147",7002);
//		jedis.auth("redis504");
//		System.out.println("==>"+jedis.get("test"));
		
		
		//测试jedis 连结
//		testJedis();
		
//		反射
//		try {
//			Class<?> clazz =  Class.forName("com.ele.item.common.config.vo.base.req.BaseAddRoleReqVo");
//			System.out.println("====>"+clazz.getSimpleName());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
