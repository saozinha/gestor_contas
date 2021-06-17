package com.lourenco.gestor_contas.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableSwagger2
public class SwaggerConfig   {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Gestor Contas Service")
                .description("gestor-contas-service reference for developers")
                .contact(new Contact("Developer", "https://www.linkedin.com/in/conceicaolourenco", "lourencosystem@gmail.com"))
                .licenseUrl("lourencosystem@gmail.com")
                .version("1.1.1")
                .build();
    }

}