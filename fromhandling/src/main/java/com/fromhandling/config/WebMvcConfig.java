package com.fromhandling.config;

import javax.servlet.ServletContext;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.fromhandling.controller"})
public class WebMvcConfig {
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = null;
		
		messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}
	@Bean
	public ServletContextTemplateResolver templateResolver(ServletContext context) {
		ServletContextTemplateResolver templateResolver = null;
		
		templateResolver = new ServletContextTemplateResolver(context);
		templateResolver.setPrefix("/WEB-INF/html/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		return templateResolver;
	}
	@Bean
	public SpringTemplateEngine templateEngine(ServletContextTemplateResolver templateResolver, MessageSource messageSource) {
		SpringTemplateEngine templateEngine = null;
		templateEngine = new SpringTemplateEngine();
		
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.setMessageSource(messageSource);
		
		return templateEngine;
	}
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = null;
		
		viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		
		return viewResolver;
	}
}
