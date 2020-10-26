package com.field.weather.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
    	/*new ApiInfo("", "", "", "", new Contact("MyAppDevTeam", "http://myapps.com", "support@myapp.com"), "", "", Collections.emptyList());
    	new ApiInfo("", "", "", "", new Contact("MyAppDevTeam", "http://myapps.com", "support@myapp.com"), "", "");
        return new ApiInfo(
                "Random Image REST API",
                "Random Image REST API",
                "1.0",
                "Terms of service",
                new Contact("MyAppDevTeam", "http://myapps.com", "support@myapp.com"),
                "License of API", "API license URL");*/
    	return null;
    }
    
    @Bean
    public RestTemplate restTemplate(List<HttpMessageConverter<?>> messageConverters) {
        return new RestTemplate(messageConverters);
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }
}