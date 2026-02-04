package java_jabki.x6_product.mappers;

import java_jabki.x6_product.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
        return Product.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .price(rs.getFloat("price"))
                .description(rs.getString("description"))
                .type(rs.getString("type"))
                .build();
    }
}
