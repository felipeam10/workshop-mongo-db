package com.felipe.workshopmongodb.config;


import com.felipe.workshopmongodb.models.entities.User;
import com.felipe.workshopmongodb.repositories.PostRepository;
import com.felipe.workshopmongodb.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void init() {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
}
