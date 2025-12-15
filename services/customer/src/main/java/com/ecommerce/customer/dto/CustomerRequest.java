package com.ecommerce.customer.dto;

import com.ecommerce.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "Customer first name is required")
        String firstName,
        @NotBlank(message = "Customer last name is required")
        String lastName,
        @Email(message = "Customer email is not a valid email address")
        @NotBlank(message = "Customer email is required")
        String email,
        Address address
) {
}
