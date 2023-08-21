package br.com.tech4me.tech4loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Tech4lojaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tech4lojaApplication.class, args);
	}

}
