package k23cnt3.plmDay04.config;

import k23cnt3.plmDay04.entity.Category;
import k23cnt3.plmDay04.entity.Product;
import k23cnt3.plmDay04.service.CategoryService;
import k23cnt3.plmDay04.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        // Initialize sample categories
        Category cat1 = new Category("Điện thoại", "Các dòng điện thoại thông minh");
        Category cat2 = new Category("Laptop", "Máy tính xách tay các loại");
        Category cat3 = new Category("Phụ kiện", "Phụ kiện công nghệ");

        categoryService.saveCategory(cat1);
        categoryService.saveCategory(cat2);
        categoryService.saveCategory(cat3);

        // Initialize sample products
        Product p1 = new Product("iPhone 14 Pro", "iPhone 14 Pro 128GB", 24990000.0);
        p1.setCategory(cat1);
        p1.setFeatured(true);
        p1.setImageUrl("https://via.placeholder.com/300x200?text=iPhone+14+Pro");
        productService.saveProduct(p1);

        Product p2 = new Product("Samsung Galaxy S23", "Samsung Galaxy S23 Ultra 256GB", 21990000.0);
        p2.setCategory(cat1);
        p2.setFeatured(true);
        p2.setImageUrl("https://via.placeholder.com/300x200?text=Galaxy+S23");
        productService.saveProduct(p2);

        Product p3 = new Product("MacBook Pro M2", "MacBook Pro 14 inch M2 chip", 42990000.0);
        p3.setCategory(cat2);
        p3.setImageUrl("https://via.placeholder.com/300x200?text=MacBook+Pro");
        productService.saveProduct(p3);

        Product p4 = new Product("AirPods Pro", "AirPods Pro 2nd generation", 5990000.0);
        p4.setCategory(cat3);
        p4.setFeatured(true);
        p4.setImageUrl("https://via.placeholder.com/300x200?text=AirPods+Pro");
        productService.saveProduct(p4);
    }
}