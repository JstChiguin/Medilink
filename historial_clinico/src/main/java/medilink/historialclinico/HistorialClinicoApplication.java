package medilink.historialclinico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HistorialClinicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistorialClinicoApplication.class, args);
	}

}
