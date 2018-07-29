package com.nuc.springMVCStudy.javaConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages= {"com.nuc.springMVCStudy"},excludeFilters= {
		@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class),
		@Filter(type=FilterType.ANNOTATION,value=Controller.class)
})
public class RootConfig {

}
