package library.demo.service;


import library.demo.model.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreateUserService  {
    @Autowired
    private CreateUserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<UserEntity> getUser(String name){
        return userRepository.findByEmail(name);
    }

    public UserEntity createUser(String name, String password, String role) {

        ObjectId objectId = new ObjectId();

        String stringId = objectId.toHexString();

        UserEntity user = new UserEntity();
        user.setId(stringId);
        user.setEmail(name);
        user.setPassword(password);
        user.setRoles(role);
        UserEntity saveUser = userRepository.save(user);
        return saveUser;
    }


}
