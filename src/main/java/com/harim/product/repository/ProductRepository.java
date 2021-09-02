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


    public List<Product> findByName(String name)
    {
        return em.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> findByPagination(int page, int size)
    {
        return em.createQuery("select p from Product p order by p.id ", Product.class)
                .setFirstResult((page-1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public void delete(Long productId) {
        Product product = em.find(Product.class, productId);
        em.remove(product);
    }
}
