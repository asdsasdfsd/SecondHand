package iss.se.team4.secondhand.service.impl;

import iss.se.team4.secondhand.model.Product;
import iss.se.team4.secondhand.model.ProductQueryString;
import iss.se.team4.secondhand.mapper.ProductMapper;
import iss.se.team4.secondhand.repository.ProductRepository;
import iss.se.team4.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    //add
    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    //delete
    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    //update
    @Override
    public void updateById(Product product) {
        productRepository.updateById(product);
    }

    @Override
    public List<Product> queryByCondition(ProductQueryString productQueryString) {
        return productMapper.queryByCondition(productQueryString);
    }


}
