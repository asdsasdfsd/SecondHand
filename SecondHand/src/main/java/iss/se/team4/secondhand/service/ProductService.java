package iss.se.team4.secondhand.service;

import iss.se.team4.secondhand.model.Product;
import iss.se.team4.secondhand.model.User;
import org.springframework.stereotype.Service;

public interface ProductService {
    /**
     * Adds a new product.
     *
     * @param product the product to be added
     */
    void add(Product product);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to be deleted
     */
    void deleteById(Integer id);

    /**
     * Updates an existing product by its ID.
     *
     * @param product the product with updated information
     */
    void updateById(Product product);
}
