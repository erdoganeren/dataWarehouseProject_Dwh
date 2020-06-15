package pacApp.pacData;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import pacApp.pacModel.Ort;

public interface OrtRepositoryM extends MongoRepository<Ort, Long> {
	Ort findById(long id);
	Ort findByOrtid(long id);
    Optional<Ort> findOneByPlz(long plz);
    void deleteByOrtid(long id);
}
