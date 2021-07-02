package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Address;
import uz.pdp.pcmarket.entity.Brand;
import uz.pdp.pcmarket.payload.AddressDto;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.BrandDto;
import uz.pdp.pcmarket.repository.AddressRepository;
import uz.pdp.pcmarket.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrand(Integer id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        return optionalBrand.orElseGet(Brand::new);
    }

    public ApiResponse addBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.getName());
        brandRepository.save(brand);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editBrand(Integer id, BrandDto brandDto) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (!optionalBrand.isPresent())
            return new ApiResponse("Brand not found", false);
        Brand brand = optionalBrand.get();
        brand.setName(brandDto.getName());
        brandRepository.save(brand);
        return new ApiResponse("Brand edited", true);
    }

    public ApiResponse deleteBrand(Integer id) {
        try {
            brandRepository.deleteById(id);
            return new ApiResponse("Brand deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
