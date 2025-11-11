package k23cnt3.plmDay04.controller;

import k23cnt3.plmDay04.service.ProductService;
import k23cnt3.plmDay04.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Trang chủ");
        model.addAttribute("featuredProducts", productService.getFeaturedProducts());
        model.addAttribute("totalProducts", productService.getActiveProductCount());
        return "user/home";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("totalProducts", productService.getActiveProductCount());
        model.addAttribute("totalCategories", categoryService.getAllCategories().size());
        model.addAttribute("featuredProducts", productService.getFeaturedProducts()); // THÊM DÒNG NÀY
        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String userProducts(Model model) {
        model.addAttribute("pageTitle", "Sản phẩm");
        model.addAttribute("products", productService.getActiveProducts());
        return "user/products";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "Giới thiệu");
        return "user/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("pageTitle", "Liên hệ");
        return "user/contact";
    }
}