package java_jabki.x6_product.model;

import lombok.Data;

@Data
public class ApiError {
    final boolean result;
    final String description;
}
