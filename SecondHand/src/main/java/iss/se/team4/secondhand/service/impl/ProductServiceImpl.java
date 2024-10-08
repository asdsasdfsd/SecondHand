package iss.se.team4.secondhand.service.impl;

import iss.se.team4.secondhand.model.Product;
import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.repository.ProductRepository;
import iss.se.team4.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

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


}
