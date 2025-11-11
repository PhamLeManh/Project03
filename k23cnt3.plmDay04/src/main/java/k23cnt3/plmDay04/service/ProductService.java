package k23cnt3.plmDay04.service;

import k23cnt3.plmDay04.entity.Product;
import k23cnt3.plmDay04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getActiveProducts() {
        List<Product> products = productRepository.findByStatusTrue();
        return products != null ? products : new ArrayList<>();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        List<Product> products = productRepository.searchProducts(keyword);
        return products != null ? products : new ArrayList<>();
    }

    public long getActiveProductCount() {
        return productRepository.countByStatusTrue();
    }

    public List<Product> getFeaturedProducts() {
        List<Product> products = productRepository.findByFeaturedTrue();
        return products != null ? products : new ArrayList<>();
    }
}