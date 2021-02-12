package ru.geekbrains.springbootapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springbootapp.persist.Product;
import ru.geekbrains.springbootapp.persist.User;
import ru.geekbrains.springbootapp.persist.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("List page requested");

        model.addAttribute("users", userRepository.findAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("user", userRepository.findById(id));
        return "user_form";
    }

    @PostMapping("/update")
    public String update(@Valid User user, BindingResult result) {
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "user_form";
        }

        if (user.getId() != null) {
            logger.info("Update user with id {}", user.getId());
            userRepository.update(user);
        } else {
            logger.info("Creating new user");
            userRepository.insert(user);
        }

        return "redirect:/user";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new user request");

        userRepository.insert(new User());
        return "redirect:/user_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("User delete request");

        userRepository.delete(id);
        return "redirect:/user";
    }
}
