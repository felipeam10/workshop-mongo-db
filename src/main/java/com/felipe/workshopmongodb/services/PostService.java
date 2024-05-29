package com.felipe.workshopmongodb.services;

import com.felipe.workshopmongodb.models.dto.PostDTO;
import com.felipe.workshopmongodb.models.dto.UserDTO;
import com.felipe.workshopmongodb.models.entities.Post;
import com.felipe.workshopmongodb.models.entities.User;
import com.felipe.workshopmongodb.repositories.PostRepository;
import com.felipe.workshopmongodb.repositories.UserRepository;
import com.felipe.workshopmongodb.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public PostDTO findById(String id) {
        Post entity = getEntityById(id);
        return new PostDTO(entity);
    }
    public Post getEntityById(String id) {
        Optional<Post> result = repository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

    public List<PostDTO> findByTitle(String text) {
        List<Post> list = repository.searchTitle(text);
        return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    public List<PostDTO> fullSearch(String text, String start, String end) {
        Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
        Instant endMoment = convertMoment(end, Instant.now());

        List<Post> list = repository.fullSearch(text, startMoment, endMoment);
        return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    private Instant convertMoment(String originalString, Instant alternative) {
        try {
            return Instant.parse(originalString);
        } catch (Exception e) {
            return alternative;
        }
    }

}
