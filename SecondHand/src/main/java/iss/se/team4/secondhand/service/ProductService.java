package iss.se.team4.secondhand.service;

import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.common.dto.AddProductDto;
import iss.se.team4.secondhand.common.dto.UpdateProductDto;
import iss.se.team4.secondhand.model.Product;
import iss.se.team4.secondhand.model.ProductQueryString;

import java.util.List;

public interface ProductService {
    /**
     * Adds a new product.
     *
     * @param addProductDto the product to be added
     */
    Result add(AddProductDto addProductDto);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to be deleted
     */
    void deleteById(Integer id);

    /**
     * Updates an existing product by its ID.
     *
     * @param updateProductDto the product with updated information
     */
    Result updateById(UpdateProductDto updateProductDto);

    /**
     * Query products by conditions and display by page.
     *
     * @param productQueryString the search conditions and page info
     */
    List<Product> queryByCondition(ProductQueryString productQueryString);

    Result queryPage(int page, int pageSize);

    Result queryWithKeyword(String keyword);

    Result queryByUsername(String username);

    Result queryAll();
}
