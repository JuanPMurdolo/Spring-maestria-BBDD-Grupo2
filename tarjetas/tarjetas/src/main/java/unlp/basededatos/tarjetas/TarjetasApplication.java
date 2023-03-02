package unlp.basededatos.tarjetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TarjetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarjetasApplication.class, args);
	}
	

}
