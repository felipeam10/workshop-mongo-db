package com.felipe.workshopmongodb.controllers;

import com.felipe.workshopmongodb.models.dto.PostDTO;
import com.felipe.workshopmongodb.models.dto.UserDTO;
import com.felipe.workshopmongodb.services.PostService;
import com.felipe.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        PostDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        List<PostDTO> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<PostDTO>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
                                                    @RequestParam(value = "start", defaultValue = "") String start,
                                                    @RequestParam(value = "end", defaultValue = "") String end) {
        List<PostDTO> list = service.fullSearch(text, start, end);
        return ResponseEntity.ok().body(list);
    }

}
