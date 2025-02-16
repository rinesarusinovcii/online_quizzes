package dev.rinesarusinovci.online_quizzes.controllers;

import dev.rinesarusinovci.online_quizzes.dto.QuizDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.entities.Question;
import dev.rinesarusinovci.online_quizzes.entities.Quiz;
import dev.rinesarusinovci.online_quizzes.entities.User;
import dev.rinesarusinovci.online_quizzes.services.ChoiceService;
import dev.rinesarusinovci.online_quizzes.services.QuestionService;
import dev.rinesarusinovci.online_quizzes.services.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;



    @GetMapping("/theQuizzes")
    public String quizzes(Model model) {
        model.addAttribute("quizzes", quizService.findAll());

        return "quizzes/theQuizzes";
    }

//    @GetMapping("/{id}/theQuizzes")
//    public String quizDetails(@PathVariable long id, Model model) {
//        var quiz = quizService.findById(id);
//        model.addAttribute("quiz", quiz);
//        return "quizzes/theQuizzes";
//    }

    @GetMapping("/newQuiz")
    public String createQuiz(Model model) {
        model.addAttribute("quizDto", new QuizDto());
        return "quizzes/newQuiz";
    }

    @GetMapping("/{id}/edit")
    public String modifyQuiz(@PathVariable long id, Model model) {
        var quiz = quizService.findById(id);
        model.addAttribute("quiz", quiz);
        return "quizzes/edit";
    }



    @PostMapping("/newQuiz")
    public String addQuizz(@Valid @ModelAttribute QuizDto quizDto, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response,
                           @SessionAttribute("user") UserDto user
    ) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "quizzes/newQuiz";
        }

        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        quizDto.setCreatedBy(userDto.getId());
        quizDto.setCreatedAt(LocalDate.now());
        quizService.add(quizDto);
        redirectAttributes.addAttribute("errorId", "SUCCESS");
        redirectAttributes.addFlashAttribute("success", "Consumer Successfully registererd!");
        return "redirect:/theQuizzes";
    }

    @PostMapping("/{id}/edit")
    public String modifyQuiz(@Valid @ModelAttribute QuizDto quizDto, @PathVariable long id, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("error", "Invalid input provided. Please fix the errors.");
            return "redirect:/quizzes/" + id + "/edit";
        }

        try {
            quizService.modify(id, quizDto);
            redirectAttributes.addFlashAttribute("success", "Quiz updated successfully!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/quizzes/" + id + "/edit";
        }

        return "redirect:/theQuizzes";
    }

    @PostMapping("/{id}/delete")
    public String removeQuiz(@PathVariable long id) {
        quizService.removeById(id);
        return "redirect:/theQuizzes";
    }

    @GetMapping("/{id}/delete")
    public String removeQuiz(@PathVariable long id,Model model) {
        var quiz = quizService.findById(id);
        model.addAttribute("quiz", quiz);
        return "quizzes/delete";
    }
}
