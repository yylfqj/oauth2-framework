package oauth.swagger.starter.autoconfigure;

import io.swagger.annotations.ApiOperation;
import oauth.swagger.starter.properties.Knife4jProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName Swagger3Configuration
 * @Author yyl
 * @Date 2022-05-10 22:55:12
 * @Description Swagger3Configuration
 * @Version 1.0
 */
@ConditionalOnProperty(value = "custom.knife4j.swagger3.enable", havingValue = "true", matchIfMissing = true)
@EnableOpenApi
public class Swagger3Configuration {

    @Autowired
    private Knife4jProperties knife4jProperties;

    @Bean
    public Docket createApi(){
        Knife4jProperties.Swagger3Properties swagger3 = knife4jProperties.getSwagger3();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo(swagger3.getApiInfo()))
                .groupName(swagger3.getGroupName())
                .globalRequestParameters(getGlobalRequestParameters(swagger3.getRequestParam()))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResponseMessage())
                .globalResponses(HttpMethod.DELETE, getGlobalResponseMessage())
                .globalResponses(HttpMethod.PUT, getGlobalResponseMessage());

    }

    /**
     * 生成接口文档基础信息
     * @return
     */
    public ApiInfo apiInfo(Knife4jProperties.Swagger3Properties.ApiInfo apiInfo){
        //API 基础信息
        return new ApiInfoBuilder()
                .title(apiInfo.getTitle())
                .description(apiInfo.getDescription())
                .version(apiInfo.getVersion())
                .contact(new Contact(apiInfo.getContact().getName(),
                        apiInfo.getContact().getUrl(),
                        apiInfo.getContact().getEmail()))
                .termsOfServiceUrl(apiInfo.getTermsOfServiceUrl())
                .build();
    }

    /**
     * 全局token
     */
    @Bean
    private SecurityScheme securityScheme() {
        return new ApiKey("token", "token", "header");
    }

    /**
     * 生成全局通用参数
     */
    private List<RequestParameter> getGlobalRequestParameters(List<Knife4jProperties.Swagger3Properties.RequestParam> requestParam) {
        if (requestParam == null || requestParam.size() == 0) {
            return null;
        }
        List<RequestParameter> parameters = new ArrayList<>();
        requestParam.stream().forEach(p -> {
            parameters.add(new RequestParameterBuilder()
                    .name(p.getName())
                    .description(p.getDescription())
                    .required(p.isRequired())
                    .in(p.getParamType())
                    .build());
        });
        return parameters;
    }

    /**
     * 生成通用响应信息
     */
    private List<Response> getGlobalResponseMessage() {
        return Arrays.asList(
                new ResponseBuilder().code("200").description("成功").build(),
                new ResponseBuilder().code("500").description("系统出错").build(),
                new ResponseBuilder().code("401").description("未认证").build(),
                new ResponseBuilder().code("403").description("无权限访问").build()
        );
    }

}
