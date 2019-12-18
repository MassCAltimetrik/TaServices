package com.taleantacq.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.taleantacq.util.EmailUtil;
import com.taleantacq.util.Excelutil;

@Configuration
public class TalentAcqConfig {

	@Bean
	public EmailUtil emailUtil() {
		return new EmailUtil();
	}

	@Bean
	@Scope("prototype")
	public Logger produceLogger(InjectionPoint injectionPoint){
	Class<?> classOnWired =injectionPoint.getMember().getDeclaringClass();
		return LoggerFactory.getLogger(classOnWired);
		} 

	
	@Bean
	public Excelutil excelutil(){
			return new Excelutil();
	}
	
	
}
