package co.in.nextgencoder.learningpath;

import co.in.nextgencoder.learningpath.config.RsaKeyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfig.class)
public class LearningPathApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningPathApplication.class, args);
	}

}
