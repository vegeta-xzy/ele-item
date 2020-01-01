package com.ele.item.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author nec
 * @DEC @Configuration 和 @Configurable 区别 ：
 *  @Configuration 用于 替代xml 文件	
 *  @Configurable 告诉Spring在构造函数运行之前将依赖注入到对象中。例： @Configurable(preConstruction = true)
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value(value = "${swagger.enabled}")
	boolean swaggerEnabled;

	@Bean
	public Docket creatApi() {
		
		/*ParameterBuilder ticketPar = new ParameterBuilder();  
        List<Parameter> pars = new ArrayList<Parameter>();    
        ticketPar.name("Authorization").description("登录校验")//name表示名称，description表示描述  
        .modelRef(new ModelRef("string")).parameterType("header")   
        .required(false).defaultValue("Bearer ").build();//required表示是否必填，defaultvalue表示默认值  
        pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效   
*/		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.enable(swaggerEnabled)//是否开启
				.select() // 选择哪些路径和api会生成document
				.apis(RequestHandlerSelectors.basePackage("com.ele.item.base.controller"))// controller路径
				.paths(PathSelectors.any()) // 对所有路径进行监控
				.build();
	}

	// 接口文档的一些基本信息
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ELE管理后台接口平台")// 文档主标题
				.description("ELE后台接口文档")// 文档描述
				.version("1.0.0")
				.termsOfServiceUrl("www.baidu.com")
				.license("饿了吃蛋炒饭")
				.licenseUrl("http://xxx.xxx.com")
				.build();
//		 Contact contact = new Contact("老王", "https://www.baidu.me", "baidu_666@icloud.com");
//         return new ApiInfo("喜卡管理后台接口平台",//大标题 title
//			"喜卡后台接口文档",//小标题
//			"1.0.1",//版本
//			"www.baidu.com",//termsOfServiceUrl
//			contact,//作者
//			"Blog",//链接显示文字
//			"https://www.baidu.me"//网站链接
//         );
	}
}
