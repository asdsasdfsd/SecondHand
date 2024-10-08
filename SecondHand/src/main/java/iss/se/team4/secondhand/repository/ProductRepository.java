package iss.se.team4.secondhand.repository;

import iss.se.team4.secondhand.model.Product;
import iss.se.team4.secondhand.model.User;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //add

    //delete
    void deleteById(int id);

    //update
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.name = :#{#product.name}, " +
            "p.description = :#{#product.description}, " +
            "p.price = :#{#product.price}, " +
            "p.categoryId = :#{#product.categoryId}, " +
            "p.sellerId = :#{#product.sellerId}, " +
            "p.publishDate = :#{#product.publishDate}, " +
            "p.status = :#{#product.status}, " +
            "p.image = :#{#product.image} " +
            "WHERE p.id = :#{#product.id}")
    void updateById(@Param("product") Product product);
}