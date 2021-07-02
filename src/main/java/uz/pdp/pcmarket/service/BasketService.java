package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.*;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.AttachmentContentDto;
import uz.pdp.pcmarket.payload.BasketDto;
import uz.pdp.pcmarket.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Basket> getBaskets() {
        return basketRepository.findAll();
    }

    public Basket getBasket(Integer id) {
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        return optionalBasket.orElseGet(Basket::new);
    }

    public ApiResponse addBasket(BasketDto basketDto) {
        Basket basket = new Basket();
        List<Product> productList=new ArrayList<>();
        for (Integer product : basketDto.getProducts()) {
            Optional<Product> optionalProduct = productRepository.findById(product);
            productList.add(optionalProduct.get());
        }
        basket.setProducts(productList);
        List<Customer> customerList=new ArrayList<>();
        for (Integer customer : basketDto.getCustomers()) {
            Optional<Customer> optionalCustomer = customerRepository.findById(customer);
            customerList.add(optionalCustomer.get());
        }
        basket.setCustomers(customerList);
        basket.setActive(true);
        basketRepository.save(basket);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editBasket(Integer id, BasketDto basketDto) {
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        if (!optionalBasket.isPresent())
            return new ApiResponse("Basket not found", false);
        Basket basket = optionalBasket.get();
        List<Product> productList=new ArrayList<>();
        for (Integer product : basketDto.getProducts()) {
            Optional<Product> optionalProduct = productRepository.findById(product);
            productList.add(optionalProduct.get());
        }
        basket.setProducts(productList);
        List<Customer> customerList=new ArrayList<>();
        for (Integer customer : basketDto.getCustomers()) {
            Optional<Customer> optionalCustomer = customerRepository.findById(customer);
            customerList.add(optionalCustomer.get());
        }
        basket.setCustomers(customerList);
        basket.setActive(basketDto.isActive());
        basketRepository.save(basket);
        return new ApiResponse("Basket edited", true);
    }

    public ApiResponse deleteBasket(Integer id) {
        try {
            basketRepository.deleteById(id);
            return new ApiResponse("Basket deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
