package com.example.project2.service;

import com.example.project2.model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {


    private final ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
                return merchantStocks;
    }

    public boolean addMerchantStocks(MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(merchantStock.getId()))
            {
                return false;
            }
        }
        merchantStocks.add(merchantStock);
                return true;
    }

    public boolean updateMerchantStocks(MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
        if (merchantStocks.get(i).getId().equals(merchantStock.getId()))
            {
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
                return false;
    }
    public boolean deleteMerchantStocks(int index){
        merchantStocks.remove(index);
        return true;

    }

    public MerchantStock getMerchantStock(String merchantID){
        for (MerchantStock merchantStock : merchantStocks)
             {
                 if(merchantStock.getId().equals(merchantID)){
                     return merchantStock;
                 }
        }
                    return null;
    }

}