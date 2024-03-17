package library.demo.service;


import library.demo.controller.Exceptions.NotFoundException;
import library.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUserService  {
    @Autowired
    private CreateUserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(String userId) {

        Query query = new Query(Criteria.where("_id").is(userId));
        UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
        if(user==null){
            throw new NotFoundException("not found "+ userId);
        }
        return user;
    }

}
