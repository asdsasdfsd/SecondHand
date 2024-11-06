package iss.se.team4.secondhand.service.impl;

import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.common.dto.AddProductDto;
import iss.se.team4.secondhand.common.dto.UpdateProductDto;
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
    public Result add(AddProductDto addProductDto) {
        if(addProductDto.getOwner() == null || addProductDto.getOwner().isEmpty()) {
            return Result.failure("owner can not be empty");
        }
        Product product = new Product();
        product.setName(addProductDto.getName());
        product.setDescription(addProductDto.getDescription());
        product.setImageUrl(addProductDto.getImageUrl());
        product.setOwner(addProductDto.getOwner());
        product.setAmount(addProductDto.getAmount());
        product.setReleaseDate(addProductDto.getReleaseDate());
        product.setStatus("published");
        product.setPrice(addProductDto.getPrice());
        productRepository.save(product);
        return Result.success();
    }

    //delete
    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    //update
    @Override
    public Result updateById(UpdateProductDto updateProductDto) {
        if(updateProductDto.getOwner() == null || updateProductDto.getOwner().isEmpty()) {
            return Result.failure("owner can not be empty");
        }
        if(!productRepository.existsById(updateProductDto.getId())) {
            return Result.failure("product does not exist");
        }
        Product product =productRepository.findById(updateProductDto.getId()).get();
        product.setName(updateProductDto.getName());
        product.setDescription(updateProductDto.getDescription());
        product.setAmount(updateProductDto.getAmount());
        product.setPrice(updateProductDto.getPrice());
        productRepository.updateById(product);
        return Result.success();
    }

    @Override
    public List<Product> queryByCondition(ProductQueryString productQueryString) {
        return productMapper.queryByCondition(productQueryString);
    }

    @Override
    public Result queryPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        try {
            return Result.success(productMapper.queryByPage(offset, pageSize));
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    @Override
    public Result queryWithKeyword(String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return Result.failure("keyword can not be empty");
        }
        try {
            List<Product> list = productMapper.queryByKeyword(keyword);
            return Result.success(list);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    @Override
    public Result queryByUsername(String username) {
        if(username == null || username.isEmpty()) {
            return Result.failure("username can not be empty");
        }

        try {
            List<Product> list = productRepository.findAllByOwner(username);
            return Result.success(list);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    @Override
    public Result queryAll() {
        try {
            List<Product> list = productRepository.findAll();
            return Result.success(list);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }


}
