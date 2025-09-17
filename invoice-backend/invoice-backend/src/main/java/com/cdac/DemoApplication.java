package com.cdac;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Configuration
	public class AppConfig {
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Configuration
	public class CorsConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("http://localhost:3000")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
					.allowCredentials(true);
		}
	}
}
