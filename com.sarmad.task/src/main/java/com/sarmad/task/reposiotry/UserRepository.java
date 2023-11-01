/**
 * 
 */
package com.sarmad.task.reposiotry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sarmad.task.entities.User;

/**
 * @author hhany
 *
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByLoginId(String loginId);

}
