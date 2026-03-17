package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ProductRepository — in-memory data store for products.
 * Uses an ArrayList to store products and a counter to generate IDs.
 *
 * YOUR TASK (Part A):
 *   Implement all 5 methods below.
 *
 * IMPORTANT: When saving a NEW product (id is null), assign it a new ID
 * using the nextId counter. When saving an EXISTING product (id is not null),
 * find and replace it in the list.
 */
@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    /**
     * Return all products.
     */
    public List<Product> findAll() {
        // TODO: Return the full list of products
        return products;
    }

    /**
     * Find a product by its ID.
     * Loop through the list and return the matching product wrapped in Optional.
     * Return Optional.empty() if not found.
     *
     * Hint: use Optional.of(product) to wrap a found product.
     */
    public Optional<Product> findById(Long id) {
        // TODO: Loop through products, find matching ID
        for (Product product : products){
            if(product.getId().equals(id)){
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    /**
     * Save a product.
     * - If the product's ID is null → assign a new ID (nextId++) and add to the list
     * - If the product's ID is NOT null → find the existing product and replace it
     *
     * @return the saved product (with ID assigned if new)
     */
    public Product save(Product product) {
        // TODO: Implement save logic for both new and existing products
        if(product.getId() == null){
            product.setId(nextId++);
            products.add(product);
        }else{
            for(int i = 0;i< products.size();i++){
                if(products.get(i).getId().equals(product.getId())){
                    products.set(i,product);
                    return product;
                }
            }
        }
        return null;
    }

    /**
     * Delete a product by ID.
     * Remove it from the list.
     *
     * @return true if found and deleted, false if not found
     */
    public boolean deleteById(Long id) {
        // TODO: Find and remove the product with matching ID
        return products.removeIf(product -> product.getId().equals(id));
    }

    /**
     * Check if a product with the given ID exists.
     */
    public boolean existsById(Long id) {
        // TODO: Return true if a product with this ID exists in the list
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Bonus
    public List<Product> findByNameContaining(String keyword) {
        List<Product> results = new ArrayList<>();
        for (Product product : products){
            if(product.getName().toLowerCase().contains(keyword.toLowerCase())){
                results.add(product);
            }
        }
        return results;
    }

    public List<Product> findByCategory(String category) {
        List<Product> results = new ArrayList<>();
        for (Product product : products){
            if(product.getCategory().toLowerCase().contains(category.toLowerCase())){
                results.add(product);
            }
        }
        return results;
    }
}
