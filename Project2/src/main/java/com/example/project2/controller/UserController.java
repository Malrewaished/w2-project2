package com.example.project2.controller;

import com.example.project2.model.Api;
import com.example.project2.model.Product;
import com.example.project2.model.User;
import com.example.project2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity addUsers(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (userService.addUsers(user)) {
            return ResponseEntity.status(200).body(new Api("New user added !", 200));
        } else
            return ResponseEntity.status(400).body(new Api("User already exist !", 400));
    }

    @PutMapping
    public ResponseEntity updateUsers(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(userService.updateUsers(user))) {
            return ResponseEntity.status(400).body(new Api("User is not exist", 400));
        }
        return ResponseEntity.status(200).body(new Api("User is updated", 200));
    }
    @DeleteMapping
    public ResponseEntity deleteUsers(@RequestBody int index, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(userService.deleteUsers(index))) {
            return ResponseEntity.status(400).body(new Api("Product not exist !", 400));
        }
        return ResponseEntity.status(200).body(new Api("Product deleted !", 200));


    }



    @PostMapping("/add")
    public ResponseEntity addProductToMerchantStock(@RequestBody String productID, @RequestBody String merchantID,
                                                     Integer stock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        userService.addProductToMerchantStock(productID, merchantID, stock);
        return ResponseEntity.status(200).body(new Api("Product added to stock!", 200));

    }

    @PostMapping("/buy")
    public ResponseEntity buyProductDirectly(@RequestParam String userId, @RequestParam String productId, String merchantId){

        Integer buyStatus = userService.buyProductDirectly(userId,productId,merchantId);

        switch (buyStatus){
            case -2:
                return ResponseEntity.status(400).body(new Api("The Product id or user id or merchant id is invalid", 400));
            case -1:
                return ResponseEntity.status(400).body(new Api("The balance of user not enough", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api("The stock is empty", 400));
            case 1:
                return ResponseEntity.status(200).body(new Api("The Product purchased", 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));

        }
    }

}
