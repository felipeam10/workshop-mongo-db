package com.felipe.workshopmongodb.services;

import com.felipe.workshopmongodb.models.dto.PostDTO;
import com.felipe.workshopmongodb.models.dto.UserDTO;
import com.felipe.workshopmongodb.models.entities.Post;
import com.felipe.workshopmongodb.models.entities.User;
import com.felipe.workshopmongodb.repositories.UserRepository;
import com.felipe.workshopmongodb.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll() {
        List<User> list = repository.findAll();
        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    public UserDTO findById(String id) {
        User entity = getEntityById(id);
        return new UserDTO(entity);
    }
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity = repository.insert(entity);
        return new UserDTO(entity);
    }

    public UserDTO update(String id, UserDTO dto) {
        User entity = getEntityById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    public void delete(String id) {
        getEntityById(id);
        repository.deleteById(id);
    }

    public List<PostDTO> getUserPosts(String id) {
        User user = getEntityById(id);
        return user.getPosts().stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
    }

    public User getEntityById(String id) {
        Optional<User> result = repository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

}
