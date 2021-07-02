package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Address;
import uz.pdp.pcmarket.payload.AddressDto;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public HttpEntity<?> getAddresses() {
        List<Address> addresses = addressService.getAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAddresses(@PathVariable Integer id) {
        Address address = addressService.getAddress(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public HttpEntity<?> addAddress(@RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAddress(@PathVariable Integer id,@RequestBody AddressDto addressDto){
        ApiResponse apiResponse = addressService.editAddress(id, addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:409).body(apiResponse);
    }
}
