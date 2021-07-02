package uz.pdp.pcmarket.payload;

import lombok.Data;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.Brand;
import uz.pdp.pcmarket.entity.Category;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class ProductDto {
    private String name;
    private double incomePrice;
    private double salePrice;
    private String description;
    private Integer categoryId;
    private Integer brandId;
    private Integer photoId;
    private int code;
    private boolean active;
    private int amount;

}
