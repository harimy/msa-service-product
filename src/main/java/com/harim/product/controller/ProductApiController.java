package com.harim.product.controller;

import com.harim.product.domain.Product;
import com.harim.product.dto.*;
import com.harim.product.repository.ProductRepository;
import com.harim.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    // GET - getProduct : id로 조회
    @GetMapping("/api/product/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id)
    {
        Product product = productService.getProduct(id);

        return new ProductDto(product.getName(), product.getPrice(), product.getStockQuantity());
    }

    // GET - getProducts : 전체 목록 조회
    // GET - getProductByPagination : 페이지 조회
    @GetMapping("/api/products")
    public GetProductResponse getAllProducts(@RequestParam(value = "page", defaultValue = "1") int offset
                                            , @RequestParam(value = "size", defaultValue = "100") int size)
    {
        List<Product> findProducts = productRepository.findByPagination(offset, size);
        List<ProductDto> collect = findProducts.stream()
                .map(p -> new ProductDto(p.getName(), p.getPrice(), p.getStockQuantity()))
                .collect(Collectors.toList());

        return new GetProductResponse(collect);
    }

    // POST - addProduct
    @PostMapping("/api/product")
    public CreateProductResponse addProduct(@RequestBody @Valid CreateProductRequest request)
    {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());

        Long id = productService.addProduct(product);
        return new CreateProductResponse(id);
    }

    // PUT - updateProduct
    @PutMapping("api/product/{id}")
    public UpdateProductResponse updateProductResponse(@PathVariable("id") Long id, @RequestBody @Valid UpdateProductRequest request)
    {
        productService.updateProduct(id, request.getName(), request.getPrice(), request.getStockQuantity());
        Product findProduct = productService.getProduct(id);
        return new UpdateProductResponse(findProduct.getId(), findProduct.getName(), findProduct.getPrice(), findProduct.getStockQuantity());
    }

    // DELETE - deleteProduct
    @DeleteMapping("api/product/{id}")
    public DeleteProductResponse deleteProductResponse(@PathVariable("id") Long id)
    {
        productService.deleteProduct(id);

        return new DeleteProductResponse(id);
    }


}
