package com.harim.product.service;

import com.harim.product.domain.Product;
import com.harim.product.exception.DuplicateProductException;
import com.harim.product.exception.ProductNotFoundException;
import com.harim.product.exception.StockQuantityException;
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
        validateDuplicateProduct(product); // 중복 상품 검증
        validateStockQuantity(product); // 재고 수량 검증
        productRepository.add(product);
        return product.getId();
    }

    private void validateDuplicateProduct(Product product)
    {
        // Exception
        List<Product> findProducts = productRepository.findByName(product.getName());
        if(!findProducts.isEmpty())
        {
            throw new DuplicateProductException();
        }
    }

    private void validateStockQuantity(Product product)
    {
        if(product.getStockQuantity() < 0)
            throw new StockQuantityException();
    }

    // 상품 수정
    @Transactional
    public void updateProduct(Long productId, String name, int price, int stockQuantity)
    {
        Product findProduct = productRepository.findById(productId);
        findProduct.setName(name);
        findProduct.setPrice(price);
        findProduct.setStockQuantity(stockQuantity);
        validateStockQuantity(findProduct);     // 재고 수량 검증
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
        Product findProduct = productRepository.findById(productId);

        return findProduct;
    }

}
