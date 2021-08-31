package com.harim.product.service;

import com.harim.product.domain.Product;
import com.harim.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 등록
    @Transactional
    public Long addProduct(Product product)
    {
        productRepository.add(product);

        return product.getId();
    }

    // 상품 수정
    @Transactional
    public void updateProduct(Long productId, String name, int price, int stockQuantity)
    {
        Product findProduct = productRepository.findById(productId);
        findProduct.setName(name);
        findProduct.setPrice(price);
        findProduct.setStockQuantity(stockQuantity);
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long productId)
    {
        productRepository.delete(productId);
    }

    // 상품 id 조회
    public Product getProduct(Long productId)
    {
        return productRepository.findById(productId);
    }

    // 상품 목록 조회
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }


}
