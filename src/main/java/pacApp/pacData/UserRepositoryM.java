package pacApp.pacData;

import pacApp.pacModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryM extends MongoRepository<User, Long>{
    User findById(long id);
    Optional<User> findOneByEmail(String email);
}
