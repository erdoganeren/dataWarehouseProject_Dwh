package pacApp.pacData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import pacApp.pacModel.Ort;

public interface OrtRepositoryM extends MongoRepository<Ort, Long> {
	Ort findById(long id);
    Optional<Ort> findOneByPlz(int plz);
}
