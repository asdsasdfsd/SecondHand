package iss.se.team4.secondhand.controller;


import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.model.Product;
import iss.se.team4.secondhand.model.ProductQueryString;
import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //add
    @PostMapping("/add")
    public Result add(@RequestBody Product product) {
        productService.add(product);
        return Result.success();
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable int id) {
        productService.deleteById(id);
        return Result.success();
    }

    //update
    @PutMapping("/update")
    public Result update(@RequestBody Product product) {
        productService.updateById(product);
        return Result.success();
    }

    //query
    @GetMapping("/query")
    public Result queryByCondition(@RequestBody ProductQueryString productQueryString) {
        productQueryString.setStartPage((productQueryString.getPage() - 1) * productQueryString.getPageSize());
        return Result.success(productService.queryByCondition(productQueryString));
    }

}
