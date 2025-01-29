//package dev.rinesarusinovci.online_quizzes.controllers;
//
//import dev.rinesarusinovci.online_quizzes.dto.UserDto;
//import dev.rinesarusinovci.online_quizzes.services.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/users")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    // Redirect to registration page for adding a new user
//
//
//    // Delete a user by ID
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.removeById(id);
//        return "redirect:/register";
//    }
//
//    // Edit a user (show edit page)
//    @GetMapping("/edit/{id}")
//    public String editUser(@PathVariable("id") Long id, Model model) {
//        UserDto userDto = userService.findById(id);
//        if (userDto != null) {
//            model.addAttribute("user", userDto.getName());
//            return "edit-user"; // Assuming "edit-user.html" is the edit page
//        } else {
//            return "redirect:/users"; // Redirect to user list if user not found
//        }
//    }
//
//    // Update a user
//    @PostMapping("/edit")
//    public String editUser(@ModelAttribute("user") UserDto userDto, BindingResult result, @PathVariable("id") Long id) {
//        if (result.hasErrors()) {
//            return "edit-user";
//        }
//        userService.modify(id, userDto);
//        return "redirect:/users";
//    }
//}
//
