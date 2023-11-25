package library.demo.service;

import library.demo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateUserRepository extends MongoRepository<User, ObjectId> {
}
