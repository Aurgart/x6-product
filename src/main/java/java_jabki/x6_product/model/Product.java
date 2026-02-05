package java_jabki.x6_product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class Product implements Serializable {
    private int id;
    private String name;
    private Float price;
    private String description;
    private String type;
}
