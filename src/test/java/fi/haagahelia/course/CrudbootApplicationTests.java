package fi.haagahelia.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.domain.Citas;
import fi.haagahelia.course.domain.PacienteRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@DataJpaTest
public class CrudbootApplicationTests {
	private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private PacienteRepository pacienteRepository;

    @Autowired
    public void setPacienteRepository(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }    
    
    @Test
    public void addUser() {
    	User user = new User("testuser", "testuser", "USER");

    	assertNull(user.getId());
    	userRepository.save(user);
    	assertNotNull(user.getId());
    }
    
	@Test
    public void addPaciente() {
		Citas paciente = new Citas("Test", "FLores", "Cardiologia", "test@test.com");
		
		pacienteRepository.save(paciente);
		Optional<Citas> findPaciente = pacienteRepository.findById(paciente.getId());
		assertTrue(findPaciente.isPresent());
    }
   
}
