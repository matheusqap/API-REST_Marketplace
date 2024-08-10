package com.github.qualquercoisavinteconto.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Address;
import com.github.qualquercoisavinteconto.repositories.AddressRepository;
import com.github.qualquercoisavinteconto.services.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address save(Address address)
    {
        return addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) throws ResourceNotFoundException
    {
        return addressRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Address> findAddressesByUser(User user)
    {
        return addressRepository.findByUser(user);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException
    {
        Address address = this.findById(id);
        addressRepository.delete(address);
    }

}
