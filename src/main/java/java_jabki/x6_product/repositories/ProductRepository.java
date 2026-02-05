package java_jabki.x6_product.repositories;

import java_jabki.x6_product.mappers.ProductMapper;
import java_jabki.x6_product.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ProductRepository {
    private static final String INSERT = """
            INSERT INTO x6_product.product(name,price,description,type)
            VALUES (:name,:price,:description,:type)
            RETURNING *;
            """;
    private static final String UPDATE = """
            UPDATE x6_product.product
            SET name = :name, price = :price, description = :description, type = :type
            WHERE id = :id
            RETURNING *;
            """;
    private static final String DELETE = """
            DELETE x6_product.product
            WHERE id = :id
            """;
    private static final String GET_BY_ID = """
            SELECT *
            FROM x6_product.product
            WHERE id = :id
            """;

    private final ProductMapper prodMapp;
    private final NamedParameterJdbcTemplate jbcTemplate;

    public Product insert(final Product item){
        return jbcTemplate.queryForObject(INSERT, ProductParamForSql(item), prodMapp);
    }
    public Product update(final Product item) {
        return jbcTemplate.queryForObject(UPDATE, ProductParamForSql(item), prodMapp);
    }

    public void delete(final int id) {
        jbcTemplate.update(DELETE, new MapSqlParameterSource("id", id));
    }

    public Product getById(final int id) {
        return jbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), prodMapp);

    }

    private MapSqlParameterSource ProductParamForSql(final Product item){
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", item.getId());
        params.addValue("name", item.getName());
        params.addValue("price", item.getPrice());
        params.addValue("description", item.getDescription());
        params.addValue("type", item.getType());

        return params;

    }

}
