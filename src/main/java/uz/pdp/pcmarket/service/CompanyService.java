package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Address;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.AttachmentContent;
import uz.pdp.pcmarket.entity.Company;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.AttachmentContentDto;
import uz.pdp.pcmarket.payload.CompanyDto;
import uz.pdp.pcmarket.repository.AddressRepository;
import uz.pdp.pcmarket.repository.AttachmentContentRepository;
import uz.pdp.pcmarket.repository.AttachmentRepository;
import uz.pdp.pcmarket.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElseGet(Company::new);
    }

    public ApiResponse addCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        company.setPhoneNumber(companyDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddresId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found",false);
        company.setAddres(optionalAddress.get());
        companyRepository.save(company);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Company company = optionalCompany.get();
        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        company.setPhoneNumber(companyDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddresId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found",false);
        company.setAddres(optionalAddress.get());
        companyRepository.save(company);
        return new ApiResponse("Company edited", true);
    }

    public ApiResponse deleteCompany(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("Company deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

}
