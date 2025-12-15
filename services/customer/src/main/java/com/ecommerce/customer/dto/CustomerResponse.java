package com.ecommerce.customer.dto;

import com.ecommerce.customer.entity.Address;

public record CustomerResponse(String id, String firstName, String lastName, String email, Address address) {
}
