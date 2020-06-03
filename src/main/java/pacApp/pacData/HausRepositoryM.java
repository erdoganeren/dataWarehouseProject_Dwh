package pacApp.pacData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import pacApp.pacModel.Haus;

public interface HausRepositoryM extends MongoRepository<Haus, Long> {
	Haus findById(long id);
}
