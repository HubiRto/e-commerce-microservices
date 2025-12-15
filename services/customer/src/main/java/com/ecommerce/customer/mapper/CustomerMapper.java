package com.ecommerce.customer.mapper;

import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.entity.Address;
import com.ecommerce.customer.entity.Customer;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    void updateCustomerFromRequest(CustomerRequest request, @MappingTarget Customer entity);
    @Mapping(target = "street", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateAddressFromRequest(Address source, @MappingTarget Address target);
    CustomerResponse toDto(Customer entity);
}
