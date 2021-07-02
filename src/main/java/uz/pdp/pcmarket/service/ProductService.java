package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.*;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.BasketDto;
import uz.pdp.pcmarket.payload.CategoryDto;
import uz.pdp.pcmarket.payload.ProductDto;
import uz.pdp.pcmarket.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {



    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseGet(Product::new);
    }

    public ApiResponse addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Category not found",false);
        product.setCategory(optionalCategory.get());

        Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
        if (!optionalBrand.isPresent())
            return new ApiResponse("Brand not found",false);
        product.setBrand(optionalBrand.get());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Photo not found",false);
        product.setPhoto(optionalAttachment.get());

        product.setActive(productDto.isActive());
        product.setDescription(productDto.getDescription());
        product.setSalePrice(productDto.getSalePrice());
        product.setIncomePrice(productDto.getIncomePrice());
        product.setAmount(productDto.getAmount());
        product.setCode(productDto.getCode());
        productRepository.save(product);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product not found", false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Category not found",false);
        product.setCategory(optionalCategory.get());

        Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
        if (!optionalBrand.isPresent())
            return new ApiResponse("Brand not found",false);
        product.setBrand(optionalBrand.get());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Photo not found",false);
        product.setPhoto(optionalAttachment.get());

        product.setActive(productDto.isActive());
        product.setDescription(productDto.getDescription());
        product.setSalePrice(productDto.getSalePrice());
        product.setIncomePrice(productDto.getIncomePrice());
        product.setAmount(productDto.getAmount());
        product.setCode(productDto.getCode());
        productRepository.save(product);
        return new ApiResponse("Product edited", true);
    }

    public ApiResponse deleteProduct(Integer id) {
        try {
            productRepository.deleteById(id);
            return new ApiResponse("Product deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
