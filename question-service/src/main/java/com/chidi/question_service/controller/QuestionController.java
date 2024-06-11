package com.chidi.question_service.controller;

import com.chidi.question_service.model.Question;
import com.chidi.question_service.model.QuestionWrapper;
import com.chidi.question_service.model.Response;
import com.chidi.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;



    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestion(){

        return questionService.getAllQuestions();

    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("category") String category){

        return questionService.getQuestionsByCategory(category);

    }


    @PostMapping("")
    public ResponseEntity<String> addQuestion(@RequestBody  Question question){
        return questionService.addQuestion(question);



    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Integer id){
        return questionService.deleteQuestion( id);



    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable("id") Integer id, @RequestBody  Question question){
        return questionService.updateQuestion( id, question);



    }

    // generate

   // getQuestions(questionid)

    //getScore

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions  ){
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);

    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}
