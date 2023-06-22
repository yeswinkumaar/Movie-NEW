package com.MovieApps.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.MovieApps.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, String>{

	User findByLoginId(String loginId);
    User findByEmail(String email);
	//User getUserByUsername(String username);

}
