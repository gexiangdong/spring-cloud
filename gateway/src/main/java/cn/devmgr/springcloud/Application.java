package cn.devmgr.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
@EnableEurekaClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        String httpUri = uriConfiguration.getHttpbin();
        return builder.routes().build();
//        return builder.routes()
//            .route(p -> p
//                .path("/get")
//                .filters(f -> f.addRequestHeader("Hello", "World"))
//                .uri(httpUri))
//            .route(p -> p
//                .host("*.hystrix.com")
//                .filters(f -> f
//                    .hystrix(config -> config
//                        .setName("mycmd")
//                        .setFallbackUri("forward:/fallback")))
//                .uri(httpUri))
//            .build();
    }
    
}

@ConfigurationProperties
class UriConfiguration {
    
    private String httpbin = "http://httpbin.org:80";

    public String getHttpbin() {
        return httpbin;
    }

    public void setHttpbin(String httpbin) {
        this.httpbin = httpbin;
    }
}