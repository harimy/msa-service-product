package com.harim.product.service;

import com.harim.product.domain.Product;
import com.harim.product.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.annotation.ExceptionProxy;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional  // 테스트 후 롤백
public class ProductServiceTest {

    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;
    @Autowired EntityManager entityManager;

    @Test
    public void 상품등록() throws Exception
    {
        // given
        Product product = new Product();
        product.setName("product1");

        // when
        Long saveId = productService.addProduct(product);

        // then
        assertEquals(product, productRepository.findById(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_상품_예외() throws Exception
    {
        // given
        Product product1 = new Product();
        product1.setName("product1");

        Product product2 = new Product();
        product2.setName("product1");

        // when
        productService.addProduct(product1);
        productService.addProduct(product2);

        // then
        fail("예외가 발생해야 한다.");
    }

}