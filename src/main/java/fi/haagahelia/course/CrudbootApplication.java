package fi.haagahelia.course;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Motivo;
import fi.haagahelia.course.domain.MotivoRepository;
import fi.haagahelia.course.domain.Citas;
import fi.haagahelia.course.domain.PacienteRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@SpringBootApplication
public class CrudbootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CrudbootApplication.class, args);
	}
	
	/**
	 * 
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(PacienteRepository repository, MotivoRepository mrepository, UserRepository urepository) {
		return (args) -> {
			
			repository.save(new Citas("Rodrigo", "Flores", "Control de Presion", "Flores@vilca.com")); 
			repository.save(new Citas("Ever", "Sanchez", "Bronquitis Aguda ", "sanchez.ever@gmail.com"));
			repository.save(new Citas("Sebastian", "Arias", "Infeccion de Oido", "Sebas@hotmail.com"));
			repository.save(new Citas("Jose", "Diaz", "Infeccion urinaria","diazq@outlook.com"));
			repository.save(new Citas("Alonso", "Cuadros", "Dolor de Garganta","alonso@gmail.com"));
			
			Stream.of("Activo", "Cancelado", "Pendiente", "Suspendido").forEach(name -> {
				mrepository.save(new Motivo(name));
			});


			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			urepository.saveAll(Arrays.asList(user1, user2));
		};
	}
}
