package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Company;
import uz.pdp.pcmarket.entity.Customer;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.CompanyDto;
import uz.pdp.pcmarket.payload.CustomerDto;
import uz.pdp.pcmarket.service.CompanyService;
import uz.pdp.pcmarket.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public HttpEntity<?> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCustomer(@PathVariable Integer id) {
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public HttpEntity<?> addCustomer(@RequestBody CustomerDto customerDto) {
        ApiResponse apiResponse = customerService.addCustomer(customerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCustomer(@PathVariable Integer id,@RequestBody CustomerDto customerDto){
        ApiResponse apiResponse = customerService.editCustomer(id, customerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCustomer(@PathVariable Integer id){
        ApiResponse apiResponse = customerService.deleteCustomer(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:409).body(apiResponse);
    }
}
