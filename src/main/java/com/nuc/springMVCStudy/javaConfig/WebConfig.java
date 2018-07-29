package com.nuc.springMVCStudy.javaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc//����HandleMapping
@ComponentScan(basePackages= {"com.nuc.springMVCStudy.controller"},includeFilters= {
		@Filter(type=FilterType.ANNOTATION,classes=Controller.class)
})
@ImportResource("classpath:webconfig.xml")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	
	
	@Override//������ת����servlet������Tomcat��Ĭ�ϵ�servlet��
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/*@Override//�յ�����Դ��ӳ��·��
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}*/

	
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	      configurer.ignoreAcceptHeader(false)
	                      .defaultContentType(MediaType.TEXT_HTML);
	}
	
	@Bean
	 public ViewResolver contentNegotiatingViewResolver(
		        ContentNegotiationManager manager) {
		    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		    resolver.setContentNegotiationManager(manager);
		    return resolver;
	}
	
	@Bean
	public ViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/jsps/", ".jsp");
		resolver.setViewClass(JstlView.class);
		resolver.setExposeContextBeansAsAttributes(true);//setExposeContextBeansAsAttributes����ʹ�ÿ�����JSPҳ��ͨ��${}����������bean��
		resolver.setOrder(1);
		return resolver;
	}
	
	
	@Bean//�������ϴ����ļ�
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(10240000);
		resolver.setResolveLazily(true);
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}
	
	
}
