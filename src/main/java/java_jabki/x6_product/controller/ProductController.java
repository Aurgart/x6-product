package java_jabki.x6_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java_jabki.x6_product.model.ApiStatus;
import java_jabki.x6_product.model.Product;
import java_jabki.x6_product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
@Tag(name = "Товары")
public class ProductController {
    private final ProductService itemLogic;


    @PostMapping
    @Operation(summary = "Создать товар")
    public Product create(@RequestBody Product item) {
        return itemLogic.addItem(item);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Инфо по товару")
    public Product getById(@PathVariable("id") String id) {
        return itemLogic.getById(Integer.parseInt(id));
    }

    @PatchMapping
    @Operation(summary = "Обновление товара.")
    public Product update(@RequestBody Product item) {
        return itemLogic.updateItem(item);
    }

    @GetMapping("/check/{id}")
    @Operation(summary = "Наличие товара")
    public ResponseEntity<ApiStatus> checkById(@PathVariable("id") String id) {
        Product item = itemLogic.getById(Integer.parseInt(id));
        if (item != null) {
            return ResponseEntity.ok().body(new ApiStatus(true, "Product " + item.getName() + " exists!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/check/id_list")
    @Operation(summary = "Наличие товаров")
    public ResponseEntity<ApiStatus> existsById(@RequestBody List<Integer> ids) {
        if (itemLogic.checkIds(ids)) {
            return ResponseEntity.ok().body(new ApiStatus(true, "Products with ids:  " + ids + " exists!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
