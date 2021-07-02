package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Address;
import uz.pdp.pcmarket.entity.Company;
import uz.pdp.pcmarket.entity.Customer;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.CompanyDto;
import uz.pdp.pcmarket.payload.CustomerDto;
import uz.pdp.pcmarket.repository.AddressRepository;
import uz.pdp.pcmarket.repository.CompanyRepository;
import uz.pdp.pcmarket.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElseGet(Customer::new);
    }

    public ApiResponse addCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(customerDto.getAddresId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found",false);
        customer.setAddres(optionalAddress.get());
        customerRepository.save(customer);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editCustomer(Integer id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent())
            return new ApiResponse("Customer not found", false);
        Customer customer = optionalCustomer.get();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(customerDto.getAddresId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found",false);
        customer.setAddres(optionalAddress.get());
        customerRepository.save(customer);
        return new ApiResponse("Customer edited", true);
    }

    public ApiResponse deleteCustomer(Integer id) {
        try {
            customerRepository.deleteById(id);
            return new ApiResponse("Customer deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
