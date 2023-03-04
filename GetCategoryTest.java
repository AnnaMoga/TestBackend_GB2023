package ru.gb.lesson_5;

import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.gb.lesson_5.dto.GetCategoryResponse;
import ru.gb.lesson_5.service.CategoryService;
import ru.gb.lesson_5.utils.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static ru.gb.lesson_5.base.enums.Category.FOOD;

public class GetCategoryTest {

    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void GetCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(FOOD.id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void GetCategoryWithResponseAssertionsPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(FOOD.id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo(FOOD.title));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo(FOOD.title)));
    }
}
