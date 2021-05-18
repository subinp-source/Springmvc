package com.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.sun.tools.classfile.Opcode.Set;

@Configuration
@ComponentScan("com.teluska")
public class ProjectConfig  {
	
	@Bean
	public InternalResourceViewResolver viewResolver() {

	InternalResourceViewResolver vr = new In InternalResourceViewResolver();
	vr.setPrefix("WEB-INF/view");
	vr.setSuffix(".jsp");
	return vr;
	}

}
