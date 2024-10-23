package iss.se.team4.secondhand.mapper;

import iss.se.team4.secondhand.model.Product;
import iss.se.team4.secondhand.model.ProductQueryString;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //query
    List<Product> queryByCondition(ProductQueryString productQueryString);

    //query by page
    List<Product> queryByPage(int offset, int limit);
}
