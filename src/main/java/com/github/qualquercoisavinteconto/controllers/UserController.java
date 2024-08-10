package com.github.qualquercoisavinteconto.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.requests.UserRequest;
import com.github.qualquercoisavinteconto.responses.UserSearchResponse;
import com.github.qualquercoisavinteconto.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@Tag(name = "Users")
@RequestMapping("users")
public class UserController {

    private final UserService service;

    public UserController(UserService service)
    {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserSearchResponse>> search() {
        var result = this.service.findAll();
        if (result == null || result.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) throws ResourceNotFoundException {
        var result = service.findById(id);
        if (result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);  
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody UserRequest userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        return service.save(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserRequest userDTO) throws ResourceNotFoundException {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        service.save(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
