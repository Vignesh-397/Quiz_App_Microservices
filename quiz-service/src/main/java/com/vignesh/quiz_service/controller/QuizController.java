package com.vignesh.quiz_service.controller;

import com.vignesh.quiz_service.model.QuestionWrapper;
import com.vignesh.quiz_service.model.Quiz;
import com.vignesh.quiz_service.model.QuizDto;
import com.vignesh.quiz_service.model.Response;
import com.vignesh.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin
public class QuizController {

    @Autowired
    private QuizService quizService;

    //generate a quiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    //take quiz
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }


    //get score when submits
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateScore(id, responses);
    }

    @GetMapping("/allQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuiz(){
        return quizService.getAllQuiz();
    }


}
