package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.AttachmentContent;
import uz.pdp.pcmarket.entity.Category;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.AttachmentContentDto;
import uz.pdp.pcmarket.payload.CategoryDto;
import uz.pdp.pcmarket.repository.AttachmentContentRepository;
import uz.pdp.pcmarket.repository.AttachmentRepository;
import uz.pdp.pcmarket.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }

    public ApiResponse addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Category not found",false);
        category.setCategoryId(optionalCategory.get());
        category.setActive(categoryDto.isActive());
        categoryRepository.save(category);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("Category not found", false);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getCategoryId());
        if (!categoryOptional.isPresent())
            return new ApiResponse("Category not found",false);
        category.setCategoryId(categoryOptional.get());
        category.setActive(categoryDto.isActive());
        categoryRepository.save(category);
        return new ApiResponse("Category edited", true);
    }

    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
