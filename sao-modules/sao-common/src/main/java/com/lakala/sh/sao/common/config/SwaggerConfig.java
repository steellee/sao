package com.lakala.sh.sao.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2配置类
 * 在与spring boot集成时，放在与Application.java同级的目录下。
 * 通过@Configuration注解，让Spring来加载该类配置。
 * 再通过@EnableSwagger2注解来启用Swagger2。
 * 访问http://localhost:8080/../swagger-ui.html
 * @author steellee
 * @date 2018/06/28
 */
@Configuration
public class SwaggerConfig {

    /**
     * 通过createRestApi函数创建Docket的Bean
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
                .apiInfo(apiInfo())
                // select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
                .select()
                // 扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求
                .apis(RequestHandlerSelectors.basePackage("com.lakala.sh.sao"))
                // 告诉Swagger暴露所有以“/staff/getstaff*”开头的路径
//                .paths(PathSelectors.regex("/staff/getstaff*"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该Api的基本信息（这些基本信息会展现在文档页面中）
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
//                .description("更多Spring Boot相关文章请关注：http://www.mypc.com/")
//                .termsOfServiceUrl("http://blog.mypc.com/")
//                .contact(new Contact("admin", "http://www.mypc.com/", "admin@gmail.com"))
                .version("1.0")
                .build();
    }
}
