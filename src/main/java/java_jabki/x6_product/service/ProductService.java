package java_jabki.x6_product.service;

import java_jabki.x6_product.exception.ProductException;
import java_jabki.x6_product.model.Product;
import java_jabki.x6_product.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository items;

    @Transactional(rollbackFor = Exception.class)
    public Product addItem(Product item){
        validateItem(item.getName(),item.getPrice());
        items.insert(item);
        return item;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "product", key = "#id")
    public Product getById(final int id){
        return items.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "product", key ="#item.id()")
    public Product updateItem(final Product item){
        return items.update(item);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value ="product", key ="#id")
    public void deleteItem(final int id){
        items.delete(id);
    }

    public Boolean checkIds(final List<Integer> ids){
        for( Integer id : ids){
            Product item = getById(id);
            if (item == null) {
                return false;
            }
        }
        return true;
    }

    private void validateItem(String name, Float price){
        if (!StringUtils.hasText(name)) {
            throw new ProductException("Item should have a name!");
        }
        if (price <= 0) {
            throw new ProductException("Price cannot be negative!");
        }
    }
}
