package iss.se.team4.secondhand.controller;


import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.common.dto.AddProductDto;
import iss.se.team4.secondhand.common.dto.UpdateProductDto;
import iss.se.team4.secondhand.model.ProductQueryString;
import iss.se.team4.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //add
    @PostMapping("/add")
    public Result add(@RequestBody AddProductDto addProductDto) {
        return productService.add(addProductDto);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable int id) {
        productService.deleteById(id);
        return Result.success();
    }

    //update
    @PutMapping("/update")
    public Result update(@RequestBody UpdateProductDto updateProductDto) {
        return productService.updateById(updateProductDto);
    }

    //condition query
    @GetMapping("/queryByCondition")
    public Result queryByCondition(@RequestBody ProductQueryString productQueryString) {
        productQueryString.setStartPage((productQueryString.getPage() - 1) * productQueryString.getPageSize());
        return Result.success(productService.queryByCondition(productQueryString));
    }

    //simple query
    @GetMapping("/query")
    public Result query(@RequestParam int page, @RequestParam int pageSize) {
        return productService.queryPage(page, pageSize);
    }

}
