package dev.rinesarusinovci.online_quizzes.controllers;

import dev.rinesarusinovci.online_quizzes.dto.ChoiceDto;
import dev.rinesarusinovci.online_quizzes.dto.QuestionDto;
import dev.rinesarusinovci.online_quizzes.dto.QuizDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.services.ChoiceService;
import dev.rinesarusinovci.online_quizzes.services.QuestionService;
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

@Controller
@RequestMapping("/choices")
@RequiredArgsConstructor
public class ChoiceController {
    private final ChoiceService choiceService;


    @GetMapping("")
    public String choices(Model model) {
        model.addAttribute("choices", choiceService.findAll());
        return "choices/details";
    }


    @GetMapping("/newChoices")
    public String addChoice(Model model) {
        model.addAttribute("choiceDto", new ChoiceDto());
        return "choices/newChoices";
    }

    @GetMapping("/{id}/edit")
    public String modifyChoice(@PathVariable long id, Model model) {
        var choice = choiceService.findById(id);
        model.addAttribute("choice", choice);
        return "choices/editChoices";
    }

//    @GetMapping("/{id}/delete")
//    public String deleteChoice(@PathVariable long id, Model model) {
//        var choice = choiceService.findById(id);
//        model.addAttribute("choice", choice);
//        return "choices/deleteChoices";
//    }

    @PostMapping("/newChoices")
    public String addChoice(@Valid @ModelAttribute ChoiceDto choiceDto, BindingResult bindingResult
            , RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "choices/newChoices";
        }


        choiceService.add(choiceDto);
        redirectAttributes.addAttribute("errorId", "SUCCESS");
        redirectAttributes.addFlashAttribute("success", "Choice Successfully registererd!");
        return "redirect:/newChoices";
    }

//    @PostMapping("/{id}/edit")
//    public String modifyChoice(@Valid @ModelAttribute ChoiceDto choiceDto, @PathVariable long id, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
//            redirectAttributes.addFlashAttribute("error", "Invalid input provided. Please fix the errors.");
//            return "redirect:/choices/" + id + "/edit";
//        }
//
//        try {
//            choiceService.modify(id, choiceDto);
//            redirectAttributes.addFlashAttribute("success", "Quiz updated successfully!");
//        } catch (IllegalStateException e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//            return "redirect:/choices/" + id + "/edit";
//        }
//
//        return "redirect:/newChoices";
//    }
//
//    @PostMapping("/{id}/delete")
//    public String removeChoice(@PathVariable long id) {
//        choiceService.removeById(id);
//        return "redirect:/newChoices";
//    }
}
