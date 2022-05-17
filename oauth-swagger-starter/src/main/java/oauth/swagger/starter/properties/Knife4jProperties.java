package oauth.swagger.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 * @ClassName Knife4jProperties
 * @Author yyl
 * @Date 2022-05-15 22:56:44
 * @Description Knife4jProperties
 * @Version 1.0
 */
@ConfigurationProperties("custom.knife4j")
public class Knife4jProperties {

    /**
     * 是否开启自定义knife4j配置
     */
    private boolean enabled = true;

    @NestedConfigurationProperty
    private Swagger3Properties swagger3 = new Swagger3Properties();

    public Swagger3Properties getSwagger3() {
        return swagger3;
    }

    public void setSwagger3(Swagger3Properties swagger3) {
        this.swagger3 = swagger3;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * swagger3配置
     */
    public static class Swagger3Properties {

        /**
         * 是否开启自定义swagger3配置
         */
        private boolean enabled = true;

        private String groupName = "分组名称";

        private List<RequestParam> requestParam;

        @NestedConfigurationProperty
        private ApiInfo apiInfo = new ApiInfo();

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public ApiInfo getApiInfo() {
            return apiInfo;
        }

        public void setApiInfo(ApiInfo apiInfo) {
            this.apiInfo = apiInfo;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<RequestParam> getRequestParam() {
            return requestParam;
        }

        public void setRequestParam(List<RequestParam> requestParam) {
            this.requestParam = requestParam;
        }

        /**
         * 接口文档基础信息
         */
        public static class ApiInfo {
            private String title = "在线文档";
            private String version = "1.0.0";
            private String description = "在线文档描述";
            private String termsOfServiceUrl = "服务url";
            private Contact contact = new Contact();

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTermsOfServiceUrl() {
                return termsOfServiceUrl;
            }

            public void setTermsOfServiceUrl(String termsOfServiceUrl) {
                this.termsOfServiceUrl = termsOfServiceUrl;
            }

            public Contact getContact() {
                return contact;
            }

            public void setContact(Contact contact) {
                this.contact = contact;
            }

            /**
             * 联络人信息
             */
            public static class Contact {
                private String name = "";
                private String url = "";
                private String email = "";

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }
            }
        }

        /**
         * 全局请求参数
         */
        public static class RequestParam {
            private String name;
            private String description;
            private boolean required;
            private String paramType;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public boolean isRequired() {
                return required;
            }

            public void setRequired(boolean required) {
                this.required = required;
            }

            public String getParamType() {
                return paramType;
            }

            public void setParamType(String paramType) {
                this.paramType = paramType;
            }
        }
    }

}
