package library.demo.service;

import library.demo.model.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreateUserRepository extends MongoRepository<UserEntity, ObjectId> {

    Optional<UserEntity> findByEmail(String email);
}
