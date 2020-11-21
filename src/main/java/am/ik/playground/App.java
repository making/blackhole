package am.ik.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication(proxyBeanMethods = false)
public class App {

	@Bean
	public RouterFunction<ServerResponse> routes() {
		return new BlackholeHandler().routes();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}
