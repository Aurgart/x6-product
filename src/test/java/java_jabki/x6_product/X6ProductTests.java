package java_jabki.x6_product;

import java_jabki.x6_product.model.Product;
import java_jabki.x6_product.repositories.ProductRepository;
import java_jabki.x6_product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class X6ProductTests {
	@Mock
	private ProductRepository itemLogic;

	@InjectMocks
	private ProductService itemService;

	@Test
	void createProductTest(){
		final Product item = testItem();
		Assertions.assertDoesNotThrow(() -> {
			itemService.addItem(item);
		});
		RuntimeException excp = assertThrows(RuntimeException.class, () -> itemService.addItem(
				Product.builder()
						.id(1)
						.name("")
						.price(100F)
						.description("Botinok")
						.type("Podkraduli")
						.build()));
		Assertions.assertNotNull(excp.getMessage());
	}


	private Product testItem() {
		return Product.builder()
				.id(1)
				.name("Kalosha")
				.price(100F)
				.description("Starii Botinok")
				.type("Shoes")
				.build();
	}

}
