package co.in.nextgencoder.springblueprint;

import co.in.nextgencoder.springblueprint.config.RsaKeyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfig.class)
public class SpringBlueprintApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBlueprintApplication.class, args);
	}

}
