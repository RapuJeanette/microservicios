package authservice.auth_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import authservice.auth_service.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
