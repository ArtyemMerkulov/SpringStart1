package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("List page requested");

        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @PostMapping("/update")
    public String update(@Valid Product product, BindingResult result) {
        logger.info("Update endpoint requested");

        if (product.getId() != null) {
            logger.info("Update product");
            productRepository.update(product);
        } else {
            logger.info("Create new product");
            productRepository.insert(product);
        }
        logger.info("product size: " + productRepository.size());
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new product request");

        productRepository.insert(new Product());
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Product delete request");

        productRepository.delete(id);
        return "redirect:/product";
    }
}
