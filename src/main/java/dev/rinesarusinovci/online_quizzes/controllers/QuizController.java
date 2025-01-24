package dev.rinesarusinovci.online_quizzes.controllers;

import dev.rinesarusinovci.online_quizzes.dto.QuizDto;
import dev.rinesarusinovci.online_quizzes.entities.Question;
import dev.rinesarusinovci.online_quizzes.entities.Quiz;
import dev.rinesarusinovci.online_quizzes.services.ChoiceService;
import dev.rinesarusinovci.online_quizzes.services.QuestionService;
import dev.rinesarusinovci.online_quizzes.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class QuizController {
    private final QuizService quizService;
    private final QuestionService questionService;
    private final ChoiceService choiceService;


}
