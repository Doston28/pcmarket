package uz.pdp.pcmarket.payload;

import lombok.Data;
import uz.pdp.pcmarket.entity.Category;

import javax.persistence.ManyToOne;

@Data
public class CategoryDto {
    private String name;
    private Integer categoryId;
    private boolean active;
}
