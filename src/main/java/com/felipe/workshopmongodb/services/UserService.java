package com.felipe.workshopmongodb.services;

import com.felipe.workshopmongodb.models.dto.UserDTO;
import com.felipe.workshopmongodb.models.entities.User;
import com.felipe.workshopmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll() {
        List<User> list = repository.findAll();
        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

}
