package com.weather.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@SuppressWarnings("rawtypes")
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage((String) "com.weather.controller"))
				.paths(PathSelectors.ant((String) "/**")).build().apiInfo(this.apiInfo())
				.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET,
						(List) Lists.newArrayList((Object[]) new ResponseMessage[] {
								new ResponseMessageBuilder().code(500).message("500 message")
										.responseModel((ModelReference) new ModelRef("Error")).build(),
								new ResponseMessageBuilder().code(403).message("Forbidden!!!!!").build() }));
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("My REST API", "API for weather forecasst.", "API TOS", "Terms of service",
				"mihirranjandas@gmail.com", "License of API", "");
		return apiInfo;
	}
}
