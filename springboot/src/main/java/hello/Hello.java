package hello;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Hello {
	@Bean
	String world(){
		return "helllo!";
	}
}
