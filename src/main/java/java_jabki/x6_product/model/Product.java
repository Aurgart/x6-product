package java_jabki.x6_product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Product implements Serializable {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String type;
}
