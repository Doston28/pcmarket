package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Basket;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.BasketDto;
import uz.pdp.pcmarket.service.BasketService;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    @Autowired
    BasketService basketService;

    @GetMapping
    public HttpEntity<?> getBaskets() {
        List<Basket> baskets = basketService.getBaskets();
        return ResponseEntity.ok(baskets);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getBasket(@PathVariable Integer id) {
        Basket basket = basketService.getBasket(id);
        return ResponseEntity.ok(basket);
    }

    @PostMapping
    public HttpEntity<?> addBasket(@RequestBody BasketDto basketDto) {
        ApiResponse apiResponse = basketService.addBasket(basketDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editBasket(@PathVariable Integer id,@RequestBody BasketDto basketDto){
        ApiResponse apiResponse = basketService.editBasket(id, basketDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteBasket(@PathVariable Integer id){
        ApiResponse apiResponse = basketService.deleteBasket(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:409).body(apiResponse);
    }
}
