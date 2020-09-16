/**  
 * flies   
 */
package cn.framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Package: cn.xpms
 * @ClassName: SwaggerConfig
 * @Description:TODO
 * @Author: Yk
 * @Date: 2020年4月12日 下午1:58:05
 * @Version 1.0
 * @Copyright:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathMapping("/")
				.select()
				//.apis(RequestHandlerSelectors.basePackage("cn.xpms.xpms.web"))
				.apis(RequestHandlerSelectors.basePackage("cn.framework"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(
						new ApiInfoBuilder().title("XPMS")
								.description("XPMS AIP详细信息......")
								.version("1.0").license("Restfule接口规范")
								.licenseUrl("http://www.baidu.com").build());
	}
}
