package com.qim.loan.util.dynamic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.io.VFS;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

@Configuration
@ConditionalOnClass({ EnableTransactionManagement.class })
@Import({ DataBaseConfig.class })
public class MybatisConfig {
	// 数据源类型
	@Value("${spring.datasource.type}")
	private Class<? extends DataSource> dataSourceType;

	@Value("${mybatis.type-aliases-package}")
	private String typeAliasesPackage;

	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;

	@Resource(name = "writeDataSource")
	private DataSource dataSource;

	@Resource(name = "readDataSource")
	private DataSource readDataSource;

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		VFS.addImplClass(SpringBootVFS.class);
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		// 设置分页
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { new PageInterceptor() });
		return sqlSessionFactoryBean.getObject();
	}

	public RoutingDataSource roundRobinDataSouceProxy() {
		RoutingDataSource proxy = new RoutingDataSource();
		proxy.setDefaultTargetDataSource(dataSource);
		Map<Object, Object> targetDataResources = new ConcurrentHashMap<Object, Object>();
		targetDataResources.put(DataSourceSwitcher.DbType.MASTER, dataSource);
		targetDataResources.put(DataSourceSwitcher.DbType.SLAVE, readDataSource);
		proxy.setDefaultTargetDataSource(dataSource);
		proxy.setTargetDataSources(targetDataResources);
		proxy.afterPropertiesSet();
		return proxy;
	}

}