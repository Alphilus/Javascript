    package com.example.demo.controller;

    import com.example.demo.model.Product;
    import com.example.demo.model.Review;
    import com.example.demo.response.PageResponse;
    import com.example.demo.response.PageResponseImpl;
    import com.example.demo.service.ProductService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.io.IOException;
    import java.util.Collections;
    import java.util.List;

    @Controller
    public class ProductController {
        @Autowired
        private ProductService productService;

        @GetMapping("/")
        public String getProducts(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int pageSize) {
            PageResponse<Product> pageData = productService.getProduct(page, pageSize);
            model.addAttribute("pageData", pageData);
            return "index";
        }

        @GetMapping("/products/{id}")
        public String getProductById(@PathVariable int id, Model model) {
            Product product = productService.getById(id);
            model.addAttribute("product", product);

            List<Review> reviews = productService.getReview();

            Collections.shuffle(reviews);

            int numberOfReviews = Math.min(3, reviews.size());

            List<Review> randomReviews = reviews.subList(0, numberOfReviews);

            model.addAttribute("reviews", randomReviews);

            return "detail";
        }
        @GetMapping("/search")
        public String searchProducts(Model model, @RequestParam String keywords) {
            List<Product> products = productService.getProductsByKeywords(keywords);
            PageResponse<Product> pageData = new PageResponseImpl<>(products);
            model.addAttribute("pageData", pageData);
            return "index";
        }

        @GetMapping("/sort")
        public String sortProducts(Model model) {
            List<Product> products = productService.getSortedProductsByRating();
            PageResponse<Product> pageData = new PageResponseImpl<>(products);
            model.addAttribute("pageData", pageData);
            return "index";
        }

        @GetMapping("/sortReverse")
        public String sortReverseProducts(Model model) {
            List<Product> products = productService.getSortedProductsByRatingReverse();
            PageResponse<Product> pageData = new PageResponseImpl<>(products);
            model.addAttribute("pageData", pageData);
            return "index";
        }
    }
