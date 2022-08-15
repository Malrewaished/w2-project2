package com.example.project2.controller;

import com.example.project2.model.Api;
import com.example.project2.model.Category;
import com.example.project2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity getCategories(){
        return ResponseEntity.status(200).body(categoryService);
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        if (categoryService.addCategories(category)) {
            return ResponseEntity.status(200).body(new Api("New category added !", 200));
        } else
            return ResponseEntity.status(400).body(new Api("Category already exist !", 400));
    }

    @PutMapping
    public ResponseEntity updateCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(categoryService.updateCategories(category))) {
            return ResponseEntity.status(400).body(new Api("The product is not exist", 400));
        }
        return ResponseEntity.status(200).body(new Api("The product is updated", 200));
    }

    @DeleteMapping
    public ResponseEntity deleteCategory(@RequestBody int index, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(categoryService.deleteCategories(index))) {
            return ResponseEntity.status(400).body(new Api("Category not exist !", 400));
        }
        return ResponseEntity.status(200).body(new Api("Category deleted !", 200));


    }
}
