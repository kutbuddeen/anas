package com.fromhandling.initializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.fromhandling.config.RootConfig;
import com.fromhandling.config.WebMvcConfig;

public class StudentMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"*.htm"};
	}
	
}
