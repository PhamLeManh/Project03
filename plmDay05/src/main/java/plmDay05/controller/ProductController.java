package plmDay05.controller;


import plmDay05.entity.Product;
import plmDay05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // Trang chủ - chuyển hướng đến danh sách sản phẩm
    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    // Hiển thị danh sách sản phẩm
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }

    // Hiển thị form tạo sản phẩm mới
    @GetMapping("/products/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-create";
    }

    // Xử lý tạo sản phẩm mới
    @PostMapping("/products/create")
    public String createProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "product-edit";
    }

    // Xử lý cập nhật sản phẩm
    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}