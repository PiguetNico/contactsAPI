package com.example.contactsAPI.swagger;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig 
{
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.contactsAPI"))
				.build()
				.apiInfo(apiDetails())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(basicAuthScheme()));
	}
	
	private ApiInfo apiDetails()
	{
		return new ApiInfo(
				"Contact API",
				"API made for Web-API Developer Challenge",
				"1.0",
				"Free to use",
				new Contact("Nicolas Piguet", null, "piguetnico@gmail.com"),
				"API Licence",
				"https://openwt.com/fr",
				Collections.emptyList());
	}
	
	private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
//                .forPaths(PathSelectors.ant("/api/**"))
                .build();
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }
}