package uz.pdp.pcmarket.payload;

import lombok.Data;
import uz.pdp.pcmarket.entity.Company;

import javax.persistence.ManyToOne;

@Data
public class UserDto {
    private String fullname;
    private String email;
    private String phoneNumber;
    private String password;
    private Integer companyId;

}
