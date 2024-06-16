package com.colab.editor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.colab.editor.content.User;

@Component
public interface UserRepository extends MongoRepository<User, String>{

    public User findByUsername(String name);
}
