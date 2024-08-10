package com.github.qualquercoisavinteconto.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Role;
import com.github.qualquercoisavinteconto.requests.RoleStoreRequest;
import com.github.qualquercoisavinteconto.services.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Tag(name = "Roles")
@RequestMapping("roles")
public class RoleController {
    private final RoleService service;
    private final UriComponentsBuilder uriBuilder;

    public RoleController(RoleService service) {
        this.service = service;
        this.uriBuilder = UriComponentsBuilder.fromUriString("");
    }

    @GetMapping
    public ResponseEntity<List<Role>> search() {
        var result = this.service.findAll();
        if (result == null || result.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> store(@RequestBody RoleStoreRequest request) {
        Role role = this.service.save(request);
        URI location = uriBuilder.path("/roles/{id}").buildAndExpand(role.getId()).toUri();
        return ResponseEntity.created(location).body(role);
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) throws ResourceNotFoundException {
        var result = this.service.findById(id);
        return ResponseEntity.ok(result);  
    }


    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody RoleStoreRequest request) throws ResourceNotFoundException {
        this.service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
