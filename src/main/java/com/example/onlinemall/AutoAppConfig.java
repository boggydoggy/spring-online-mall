package com.example.onlinemall;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
		excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
		// includeFilters = @Filter(type = FilterType.ANNOTATION, classes = Service.class),
		// basePackages = {"com.example.onlinemall.member", "com.example.onlinemall.order"}
)
public class AutoAppConfig {

}
