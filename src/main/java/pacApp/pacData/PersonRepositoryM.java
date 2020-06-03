package pacApp.pacData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import pacApp.pacModel.Person;

public interface PersonRepositoryM extends MongoRepository<Person, Long> {
	Person findById(long id);
	Optional<Person> findOneByNachname(String nachname);
}
