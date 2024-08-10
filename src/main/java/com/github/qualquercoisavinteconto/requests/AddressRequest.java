package com.github.qualquercoisavinteconto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @JsonProperty("user_id")
    private Long userId;
    private String street;
    private String number;
    private String state;
    private String city;
  
}
