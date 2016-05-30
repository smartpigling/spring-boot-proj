package com.proj.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.proj.admin.filter.LoginSuccessHandler;
import com.proj.admin.service.impl.AdminUserDetailsService;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger =LoggerFactory.getLogger(WebSecurityConfig.class);

	private static final String[] UNSECURED_RESOURCE_LIST = new String[] {"/bootstrap/**", "/libs/**",
			"/plugins/**", "/theme/**", "/images/**", "/h2-console/**"};

	private static final String[] UNAUTHORIZED_RESOURCE_LIST = new String[] {"/test","/login","perform_login","/getCaptcha", "/unauthorized*",
			"/error*", "/exception*", "/accessDenied*" };

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(UNSECURED_RESOURCE_LIST);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
		http
			.headers()
				.frameOptions()
				.sameOrigin()
			.and()
				.authorizeRequests()
					.antMatchers(UNAUTHORIZED_RESOURCE_LIST).permitAll()
					.anyRequest().authenticated()
			.and()
				.formLogin()
					.failureUrl("/login?error") 
					.defaultSuccessUrl("/")
					.loginPage("/login")
					.successHandler(loginSuccessHandler()) //登录成功后处理
					.permitAll()
			.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login")
					.invalidateHttpSession(true)
					.deleteCookies("remember-me")
					.permitAll()
//			.and()
//				.exceptionHandling()
//					.accessDeniedPage("/access?error")
			.and()
				.rememberMe() //数据库表persistent_logins
//				.tokenRepository(tokenRepository())  //制定登录信息数据源
					.useSecureCookie(true)
					.tokenValiditySeconds(60 * 60 * 24 * 10) 
			.and()
				.sessionManagement()
					.maximumSessions(1)
					.expiredUrl("/login?expired");

	}

	@Autowired
	private AdminUserDetailsService adminUserDetailsService;
	
	
//	@Autowired
//	@Qualifier("dataSource")
//	private DataSource dataSource;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(adminUserDetailsService).passwordEncoder(passwordEncoder());
		auth.eraseCredentials(false); //不删除凭据，remember 用户
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(4);
	}
	
	
//	@Bean
//	public JdbcTokenRepositoryImpl tokenRepository(){
//		JdbcTokenRepositoryImpl j =new JdbcTokenRepositoryImpl();
//		j.setDataSource(dataSource);
//		return j;
//	}
	
	
	@Bean
	public LoginSuccessHandler loginSuccessHandler(){
		return new LoginSuccessHandler();
	}

	
}