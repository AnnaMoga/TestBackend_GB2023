package ru.gb.lesson_5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.gb.lesson_5.base.enums.Category;
import ru.gb.lesson_5.dto.Product;
import ru.gb.lesson_5.service.ProductService;
import ru.gb.lesson_5.utils.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;

public class CreateProductTest {

  static ProductService productService;
  Product product;
  Faker faker = new Faker();
  int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle(Category.FOOD.title)
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    @SneakyThrows
    void createFoodInCategoryTest() {
      Response<Product> response = productService.createProduct(product)
              .execute();
         id = response.body().getId();
         assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
      Response<ResponseBody> response = productService.deleteProduct(id)
              .execute();
      assertThat(response.isSuccessful(), CoreMatchers.is(true));
  }
}
