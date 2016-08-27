package com.sumit.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer  {

	public Initializer(){}
	
	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebAppConfig.class);
		servletContext.addListener(new ContextLoaderListener(ctx));

		servletContext.addFilter("simpleCorsFilter", "com.sumit.init.SimpleCORSFilter");
		ctx.setServletContext(servletContext);
		Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME,

				new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		 
	}
}
