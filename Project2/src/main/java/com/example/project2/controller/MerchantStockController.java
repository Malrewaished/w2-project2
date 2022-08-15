package com.example.project2.controller;

import com.example.project2.model.Api;
import com.example.project2.model.MerchantStock;
import com.example.project2.service.MerchantStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

private final MerchantStockService merchantStockService;


    @GetMapping
    public ResponseEntity getMerchantStocks(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
        }

    @PostMapping
    public ResponseEntity addMerchantStocks(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        if (merchantStockService.addMerchantStocks(merchantStock)) {
            return ResponseEntity.status(200).body(new Api("New merchantStock added !", 200));
        } else
            return ResponseEntity.status(400).body(new Api("MerchantStock already exist !", 400));
    }

    @PutMapping
    public ResponseEntity updateMerchantStocks(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(merchantStockService.updateMerchantStocks(merchantStock))) {
            return ResponseEntity.status(400).body(new Api("MerchantStock is not exist", 400));
        }
        return ResponseEntity.status(200).body(new Api("MerchantStock is updated", 200));
    }
    @DeleteMapping
    public ResponseEntity deleteMerchantStocks(@RequestBody int index, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(merchantStockService.deleteMerchantStocks(index))) {
            return ResponseEntity.status(400).body(new Api("MerchantStock not exist !", 400));
        }
        return ResponseEntity.status(200).body(new Api("MerchantStock deleted !", 200));


    }

}
