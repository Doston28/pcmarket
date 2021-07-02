package uz.pdp.pcmarket.payload;

import lombok.Data;
import uz.pdp.pcmarket.entity.Address;

import javax.persistence.OneToOne;

@Data
public class CompanyDto {
    private String name;
    private String phoneNumber;
    private String email;
    private Integer addresId;


}
