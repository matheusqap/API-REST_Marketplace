package com.github.qualquercoisavinteconto.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Ads;
import com.github.qualquercoisavinteconto.requests.AdsRequest;
import com.github.qualquercoisavinteconto.services.impl.AdsServiceImpl;

@RestController
@Tag(name = "Ads")
@RequestMapping("ads")
public class AdsController {

    private final AdsServiceImpl adsService;

    public AdsController(AdsServiceImpl adsService) {
        this.adsService = adsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ads save(@RequestBody AdsRequest adsDTO) {
        return adsService.save(adsDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ads> getById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(adsService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AdsRequest ads) throws ResourceNotFoundException {
        adsService.update(id, ads);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}