package com.github.qualquercoisavinteconto.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.github.qualquercoisavinteconto.services.AddressService;
import com.github.qualquercoisavinteconto.services.UserService;
import com.github.qualquercoisavinteconto.models.Address;
import com.github.qualquercoisavinteconto.requests.AddressRequest;
import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@Tag(name = "Address")
@RequestMapping("/addresses")
public class AddressController {
    private final UserService userService;
    private final AddressService addressService;

    public AddressController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address save(@RequestBody AddressRequest addressDTO) throws ResourceNotFoundException {
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setNumber(addressDTO.getNumber());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setUser(userService.findById(addressDTO.getUserId()));
        return addressService.save(address);
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Address>> getAddressByUser(@PathVariable Long userId) throws ResourceNotFoundException {
        List<Address> addresses = addressService.findAddressesByUser(userService.findById(userId));
 
        if(addresses == null || addresses.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(addresses);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) throws ResourceNotFoundException {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody AddressRequest addressDTO) throws ResourceNotFoundException {
        Address address = addressService.findById(id);
        address.setCity(addressDTO.getCity());
        address.setNumber(addressDTO.getNumber());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setUser(userService.findById(addressDTO.getUserId()));
        return ResponseEntity.ok(addressService.save(address));
    }
}