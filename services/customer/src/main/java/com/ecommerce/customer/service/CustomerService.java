package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.mapper.CustomerMapper;
import com.ecommerce.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toEntity(request));
        return customerMapper.toDto(customer);
    }

    public CustomerResponse updateCustomer(String id, CustomerRequest request) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(
                format("Cannot update customer:: No customer found with the provided ID:: %s", id)
        ));
        customerMapper.updateCustomerFromRequest(request, customer);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    public Boolean existById(String id) {
        return customerRepository.existsById(id);
    }

    public CustomerResponse findById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException(format("No customer found with the provided ID:: %s", id)));
    }

    public void deleteCustomerById(String id) {
        customerRepository.deleteById(id);
    }
}
