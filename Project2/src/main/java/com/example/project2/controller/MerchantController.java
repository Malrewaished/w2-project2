package com.example.project2.controller;

import com.example.project2.model.Api;
import com.example.project2.model.Merchant;
import com.example.project2.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;


    @GetMapping
    public ResponseEntity getMerchant(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        if (merchantService.addMerchant(merchant)) {
            return ResponseEntity.status(200).body(new Api("New merchant added !", 200));
        } else
            return ResponseEntity.status(400).body(new Api("Merchant already exist !", 400));
    }

    @PutMapping
    public ResponseEntity updateMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(merchantService.updateMerchant(merchant))) {
            return ResponseEntity.status(400).body(new Api("Merchant is not exist", 400));
        }
        return ResponseEntity.status(200).body(new Api("Merchant is updated", 200));
    }

    @DeleteMapping
    public ResponseEntity deleteMerchant(@RequestBody int index, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(merchantService.removeMerchant(index))) {
            return ResponseEntity.status(400).body(new Api("Merchant not exist !", 400));
        }
        return ResponseEntity.status(200).body(new Api("Merchant deleted !", 200));


    }
}
