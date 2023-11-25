package library.demo.service;


import library.demo.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    @Autowired
    private CreateUserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public User createUser(String name, String password){

        ObjectId objectId = new ObjectId();

        String stringId = objectId.toHexString();

        User user = new User();
            user.setId(stringId);
            user.setName(name);
            user.setPassword(password);

        User saveUser = userRepository.save(user);
        return saveUser;
    }
}
