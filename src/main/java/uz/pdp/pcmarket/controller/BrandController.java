package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Basket;
import uz.pdp.pcmarket.entity.Brand;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.BasketDto;
import uz.pdp.pcmarket.payload.BrandDto;
import uz.pdp.pcmarket.service.BasketService;
import uz.pdp.pcmarket.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping
    public HttpEntity<?> getBrands() {
        List<Brand> brands = brandService.getBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getBrand(@PathVariable Integer id) {
        Brand brand = brandService.getBrand(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping
    public HttpEntity<?> addBrand(@RequestBody BrandDto brandDto) {
        ApiResponse apiResponse = brandService.addBrand(brandDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editBrand(@PathVariable Integer id,@RequestBody BrandDto brandDto){
        ApiResponse apiResponse = brandService.editBrand(id, brandDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteBrand(@PathVariable Integer id){
        ApiResponse apiResponse = brandService.deleteBrand(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:409).body(apiResponse);
    }
}
