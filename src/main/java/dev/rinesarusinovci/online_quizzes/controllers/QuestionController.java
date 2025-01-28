package dev.rinesarusinovci.online_quizzes.controllers;

import dev.rinesarusinovci.online_quizzes.dto.QuestionDto;
import dev.rinesarusinovci.online_quizzes.services.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("")
    public String questions(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "question/newQuestion";
    }

//    @GetMapping("/{id}/details")
//    public String questionDetails(@PathVariable long id, Model model) {
//        var question = questionService.findById(id);
//        model.addAttribute("quiz", question);
//        return "quizzes/details";
//    }

    @GetMapping("/create")
    public String createQuestion(Model model) {
        model.addAttribute("questionDto", new QuestionDto());
        return "question/create";
    }

    @GetMapping("/{id}/edit")
    public String modifyQuestion(@PathVariable long id, Model model) {
        var question = questionService.findById(id);
        model.addAttribute("question", question);
        return "question/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteQuestion(@PathVariable long id, Model model) {
        var question = questionService.findById(id);
        model.addAttribute("quiz", question);
        return "question/delete";
    }

    @PostMapping("/create")
    public String addQuestion(@Valid @ModelAttribute QuestionDto questionDto, BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "question/new";
        }


        questionService.add(questionDto);
        redirectAttributes.addAttribute("errorId", "SUCCESS");
        redirectAttributes.addFlashAttribute("success", "Consumer Successfully registererd!");
        return "redirect:/newQuestion";
    }

    @PostMapping("/{id}/edit")
    public String modifyQuestion(@Valid @ModelAttribute QuestionDto questionDto, @PathVariable long id, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("error", "Invalid input provided. Please fix the errors.");
            return "redirect:/question/" + id + "/edit";
        }

        try {
            questionService.modify(id, questionDto);
            redirectAttributes.addFlashAttribute("success", "Question updated successfully!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/question/" + id + "/edit";
        }

        return "redirect:/newQuestion";
    }

    @PostMapping("/{id}/delete")
    public String removeQuestion(@PathVariable long id) {
        questionService.removeById(id);
        return "redirect:/newQuestion";
    }
}
