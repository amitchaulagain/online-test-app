package com.sumit.init;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:com/sumit/init/spring-security.xml")
public class SecurityConfig {

}