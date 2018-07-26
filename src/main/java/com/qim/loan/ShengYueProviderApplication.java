package com.qim.loan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.qim.loan.util.interceptor.TokenInterceptor;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan({ "com.qim.loan.core.dao", "com.qim.loan.dao" })
@ServletComponentScan
@ComponentScan(basePackages = "com.qim.loan")
@EnableDiscoveryClient
@RestController
public class ShengYueProviderApplication extends WebMvcConfigurationSupport {

	@Autowired(required = true)
	private TokenInterceptor tokenInterceptor;


	public static void main(String[] args) {
		SpringApplication.run(ShengYueProviderApplication.class, args);
	}

	/**
	 * 
	 * 方法名:dispatcherRegistration 功能描述:增加特定后缀 创建者:冯子文 创建时间: 2018年2月19日 下午9:34:29
	 * 更新者:冯子文 更新时间: 2018年2月19日 下午9:34:29
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.getUrlMappings().clear();
		//registration.addUrlMappings("*.do");
		//registration.addUrlMappings("*.app");
		System.out.println("5656的萨丁♯飒点");
		return registration;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*registry.addInterceptor(tokenInterceptor)
		.excludePathPatterns("/channel/user/login") //渠道用户登录
		.excludePathPatterns("/employee/user/login")//部门二用户登录
		.excludePathPatterns("/client/retention")//用户留资
		.excludePathPatterns("/client/getSelect")//获取服务用户
		.excludePathPatterns("/client/getByEmployeeId")//获取服务用户电话
		.excludePathPatterns("/employeem/user/loginm")
		.excludePathPatterns("/consoleuser/user/login")
		.excludePathPatterns("/client/getNameByNumber")
		.excludePathPatterns("/client/getEmployeeTwoIdByNumber")
		.excludePathPatterns("/distributeUser/login")
		.excludePathPatterns("/client/getEmployeeOneNumberByTelphoneNumber");*/
		
		
		//
		
		//.excludePathPatterns("")
	}

}
