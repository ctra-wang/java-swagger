package com.ctra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration // 添加配置
@EnableSwagger2 //开启 swagger
public class SwaggerConfig {

    @Bean
    public Docket docket1(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2).groupName("docket1");
    }

    // 配置了 swagger 的 docket 实例
    @Bean
    public Docket docket(Environment environment){

        // 设置需要登录的 swagger 环境
        Profiles profiles = Profiles.of("pro","dev");
        System.out.println(environment);
        // 获取当前设置的环境
        boolean flag = environment.acceptsProfiles(profiles);
        System.out.println(flag);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("wanglei")
                .apiInfo(apiInfo())
                // enable 是否启动 swagger，如果为false，则 swagger不能再浏览器中访问
                .enable(flag)
                .select()
                // RequestHandlerSelectors：配置要扫描接口的方式
                //  -basePackage：指定要扫描的包 ☆
                //  -any()：扫描全部
                //  -none()：不扫描
                //  -withClassAnnotation：扫描类上的注解，参数是一个注解的反射对象
                //  -withMethodAnnotation：扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.ctra.controller"))
                // 过滤条件
//                .paths(PathSelectors.none())
                .build();
    }

    private ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("wanglei", "www.baidu.com", "316434776@qq.com");
        return  new ApiInfo(
                "王磊的API",
                "CTRA",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
