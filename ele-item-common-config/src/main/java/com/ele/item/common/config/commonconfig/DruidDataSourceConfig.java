package com.ele.item.common.config.commonconfig;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.ele.item.common.config.util.AESUtil;

import lombok.extern.slf4j.Slf4j;


@Configuration
@PropertySource(value= {"classpath:${spring.profiles.include}/application-common-dev-druid.properties"})//只能识别 *.properties或者 *.xml (*.yml 可寻找其他方式)
@Slf4j
public class DruidDataSourceConfig {

	@Value("${ele.driverClassName}")
	private String driverClassName;

	@Value("${ele.dbUrl}")
	private String dbUrl;

	@Value("${ele.username}")
	private String username;

	@Value("${ele.password}")
	private String password;

	@Value("${ele.initialSize}")
	private int initialSize;// 连接初始值，连接池启动时创建的连接数量初始值

	@Value("${ele.maxActive}")
	private int maxActive;// 连接池的最大值，同一时间可以从池分配的最多连接数量，0时无限制

//	@Value("${ele.maxIdle}")
//	private String maxIdle;//大空闲值.当经过一个高峰时间后，连接池可以慢慢将已经用不到的连接慢慢释放一部分，一直减少到maxIdle为止 ，0时无限制

	@Value("${ele.minIdle}")
	private int minIdle;// 最小空闲值.当空闲的连接数少于阀值时，连接池就会预申请去一些连接，以免洪峰来时来不及申请

	@Value("${ele.poolPreparedStatements}")
	private boolean poolPreparedStatements;// 是否对已备语句进行池管理（布尔值），是否对PreparedStatement进行缓存 , mysql(较多的分库分表) 建议用false
											// oracle 用true

	@Value("${ele.maxPoolPreparedStatementPerConnectionSize}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Value("${ele.maxWait}")
	private long maxWait;// 配置获取连接等待超时时间

	@Value("${ele.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒

	@Value("${ele.minEvictableIdleTimeMillis}")
	private long minEvictableIdleTimeMillis;// 配置一个连接在池中最小生存的时间，单位是毫秒

	/** 待了解 start **/
	@Value("${ele.validationQuery}")
	private String validationQuery;

	@Value("${ele.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${ele.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${ele.testOnReturn}")
	private boolean testOnReturn;

	@Value("${ele.connectionProperties}")
	private String connectionProperties;

	@Value("${ele.aesEncrypt}")
	private String aesEncrypt;
	
	@Value("${ele.logSlowSql}")
	private String logSlowSql;

	/** 待了解 end **/

	@Value("${ele.filters}")
	private String filters;// 配置监控统计拦截的filters
	
	@Bean
	public DataSource druidDataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(AESUtil.decrypt(password, aesEncrypt));
		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		datasource.setConnectionProperties(connectionProperties);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			 log.error("druid configuration initialization filter cause SQLException : " + e.getMessage(), e);
		}
		return datasource;
	}
	
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidServlet() {
		ServletRegistrationBean<StatViewServlet> srb = new ServletRegistrationBean<StatViewServlet>();
		srb.setServlet(new StatViewServlet());
		srb.addUrlMappings("/druid/*");
		//servletRegistrationBean.addInitParameter("allow", "127.0.0.1"); // 设置ip白名单
		//servletRegistrationBean.addInitParameter("deny", "192.168.0.19");// 设置ip黑名单，优先级高于白名单
		// 设置控制台管理用户
		srb.addInitParameter("loginUsername", username);
		srb.addInitParameter("loginPassword", AESUtil.decrypt(password, aesEncrypt));
		srb.addInitParameter("logSlowSql", logSlowSql);
		return srb;
	}

	@Bean
	public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();// 创建过滤器
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");// 设置过滤器过滤路径
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");// 忽略过滤的形式
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		return filterRegistrationBean;
	}
	
	public static void main(String[] args) {
		System.out.println("==>"+AESUtil.encrypt("F2@H0<zlD1>9?no", "Element@2019"));
	}

}
