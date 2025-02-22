package dev.rinesarusinovci.online_quizzes.controllers;

import dev.rinesarusinovci.online_quizzes.dto.LoginDto;
import dev.rinesarusinovci.online_quizzes.dto.RegisterUserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.exception.EmailExistsException;
import dev.rinesarusinovci.online_quizzes.exception.UserNotFoundException;
import dev.rinesarusinovci.online_quizzes.exception.UsernameExistsException;
import dev.rinesarusinovci.online_quizzes.exception.WrongPasswordException;
import dev.rinesarusinovci.online_quizzes.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller

public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginRequestDto,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "returnUrl", required = false) String returnUrl) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        try {
            var userDto = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());


            HttpSession session = request.getSession();
            session.setAttribute("user", userDto);

            Cookie cookie = new Cookie("userId", "" + userDto.getId());
            if (loginRequestDto.isRememberMe()) {
                cookie.setMaxAge(60 * 60 * 24 * 30);
            } else {
                cookie.setMaxAge(60 * 60);
            }

            response.addCookie(cookie);
            if (returnUrl == null || returnUrl.isBlank())
                return "redirect:/";
            return "redirect:" + returnUrl;
        } catch (UserNotFoundException e) {
            bindingResult.rejectValue("email", "error.loginRequestDto",
                    e.getMessage());
            return "auth/login";
        } catch (WrongPasswordException e) {
            bindingResult.rejectValue("password", "error.loginRequestDto",
                    e.getMessage());
            return "auth/login";
        }
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDto());
        model.addAttribute("maxDate", LocalDate.now().minusYears(18));
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterUserDto registerUserRequestDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

//        if (!registerUserRequestDto.getPassword().equals(registerUserRequestDto.getConfirmPassword())) {
//            bindingResult.rejectValue("password", "error.registerUserRequestDto", "Passwords do not match");
//            bindingResult.rejectValue("confirmPassword", "error.registerUserRequestDto", "Passwords do not match");
//            return "auth/register";
//        }

        try {
            userService.register(registerUserRequestDto);
        } catch (UsernameExistsException e) {
            bindingResult.rejectValue("username", "error.registerUserRequestDto", "This username already exists");
            return "auth/register";
        } catch (EmailExistsException e) {
            bindingResult.rejectValue("email", "error.registerUserRequestDto", "This email already exists");
            return "auth/register";
        }

        return "redirect:/login";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("userId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/login";
    }



    @GetMapping("/edit-profile")
    public String showEditProfileForm(HttpSession session, Model model) {
        UserDto loggedInUser = (UserDto) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("userDto", loggedInUser);
        return "auth/edit-profile";
    }



    @PostMapping("/edit-profile")
    public String editProfile(@Valid @ModelAttribute UserDto userDto,
                              BindingResult bindingResult,
                              HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "auth/edit-profile";
        }

        UserDto loggedInUser = (UserDto) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        try {
            UserDto updatedUser = userService.modify(loggedInUser.getId(), userDto);
            session.setAttribute("user", updatedUser);
        } catch (UserNotFoundException e) {
            bindingResult.rejectValue("email", "error.userDto", e.getMessage());
            return "auth/edit-profile";
        }

        return "redirect:/";
    }


    @PostMapping("/delete-profile")
    public String deleteProfile(HttpSession session, HttpServletResponse response) {
        UserDto loggedInUser = (UserDto) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        userService.removeById(loggedInUser.getId());
        session.invalidate();
        Cookie cookie = new Cookie("userId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/register";
    }

    @GetMapping("/delete-profile")
    public String deleteProfile(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/delete-profile";
    }

    @GetMapping("/auth/settings")
    public String settings(Model model) {
        return "auth/settings";
    }


}
