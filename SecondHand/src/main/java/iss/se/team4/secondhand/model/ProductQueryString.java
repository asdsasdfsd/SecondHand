package iss.se.team4.secondhand.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryString {
    private String description;
    private Integer categoryId;
    private Double maxPrice;
    private Double minPrice;
    private String status;
    private Integer pageSize;
    private Integer page;
    private Integer startPage;
}
