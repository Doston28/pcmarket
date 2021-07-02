package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Category;
import uz.pdp.pcmarket.entity.Company;
import uz.pdp.pcmarket.entity.User;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.CategoryDto;
import uz.pdp.pcmarket.payload.UserDto;
import uz.pdp.pcmarket.repository.CategoryRepository;
import uz.pdp.pcmarket.repository.CompanyRepository;
import uz.pdp.pcmarket.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }

    public ApiResponse addUser(UserDto userDto) {
        User user = new User();
        user.setFullname(userDto.getFullname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        Optional<Company> optionalCompany = companyRepository.findById(userDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found",false);
        user.setCompany(optionalCompany.get());
        userRepository.save(user);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User not found", false);
        User user = optionalUser.get();
        user.setFullname(userDto.getFullname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        Optional<Company> optionalCompany = companyRepository.findById(userDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found",false);
        user.setCompany(optionalCompany.get());
        userRepository.save(user);
        return new ApiResponse("User edited", true);
    }

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("User deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
