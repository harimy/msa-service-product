package com.harim.product.repository;

import com.harim.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public void add(Product product)
    {
        em.persist(product);
    }

    public Product findById(Long id)
    {
        return em.find(Product.class, id);
    }

    public List<Product> findAll()
    {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public void delete(Long productId) {
        Product product = em.find(Product.class, productId);
        em.remove(product);
    }
}
