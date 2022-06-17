package com.ademiju.demoThymeLeaf.controllers;

import com.ademiju.demoThymeLeaf.datas.models.User;
import com.ademiju.demoThymeLeaf.datas.repositories.UserRepository;
import com.ademiju.demoThymeLeaf.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sign-up")
    public String showSignUpPage(User user) {
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        model.addAttribute("user", user);
        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
//    @GetMapping("/index")
//    public ModelAndView showAllUsers(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return new ModelAndView("index", "users", model);
//    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) throws ApplicationException {
        User user = userRepository.findById(id).orElseThrow(() -> new ApplicationException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-form";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-form";
        }
        userRepository.save(user);
        return "redirect:/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) throws ApplicationException {
        User user = userRepository.findById(id).orElseThrow(() -> new ApplicationException("User not found"));
        userRepository.delete(user);
        return "redirect:/index";
    }

}

