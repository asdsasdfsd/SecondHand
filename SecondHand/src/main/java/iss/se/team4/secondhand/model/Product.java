package iss.se.team4.secondhand.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    // todo: foreign key
    private Integer categoryId;
    private Double price;
    // todo: foreign key
    private String releaseDate;
    private String status;
    private String imageUrl;
    private String owner;
    private Integer amount;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", String=" + owner +
                ", publishDate=" + releaseDate +
                ", status='" + status + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(categoryId, product.categoryId) && Objects.equals(price, product.price) && Objects.equals(owner, product.owner) && Objects.equals(releaseDate, product.releaseDate) && Objects.equals(status, product.status) && Objects.equals(imageUrl, product.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, categoryId, price, owner, releaseDate, status, imageUrl);
    }
}
