package uz.pdp.pcmarket.payload;

import lombok.Data;
import uz.pdp.pcmarket.entity.Customer;
import uz.pdp.pcmarket.entity.Product;
import java.util.List;

@Data
public class BasketDto {
    private List<Integer> products;
    private List<Integer> customers;
    private boolean active;

}
