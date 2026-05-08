package medilink.cita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class CitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitaApplication.class, args);
	}

}
