package uz.pdp.pcmarket.payload;

import lombok.Data;

@Data
public class CustomerDto {
    private String name;
    private String phoneNumber;
    private String email;
    private Integer addresId;

}
