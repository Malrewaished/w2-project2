package com.example.project2.service;

import com.example.project2.model.MerchantStock;
import com.example.project2.model.Product;
import com.example.project2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    private final MerchantStockService merchantStockService;
    private final ProductService productService;


    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean addUsers(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public boolean updateUsers(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())){
                users.set(i, user);
                return true;
        }
    }
            return false;
}

    public boolean deleteUsers(int index){
        users.remove(index);
        return true;
    }

    public boolean addProductToMerchantStock(String productID, String merchantID, Integer stock){
        Product currentProduct = productService.getProduct(productID);
        MerchantStock currentMerchantStock = merchantStockService.getMerchantStock(merchantID);
        currentMerchantStock.setStock(Integer.valueOf(productID));
        return true;
    }

    public User getUserById(String userId){
        for (User user : users)
        {
            if(user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }


    public Integer buyProductDirectly(String userId, String productId, String merchantId){
        User currUser = getUserById(userId);
        Product currProduct = productService.getProduct(productId);
        MerchantStock currMerchantStock = merchantStockService.getMerchantStock(merchantId);

        if(currUser == null || currProduct == null || currMerchantStock == null ){
            return -2;
        }

        if( currUser.getBalance() < currProduct.getPrice()){
            return -1;
        }

        if(!(currMerchantStock.getProductID().equals(productId))){
            return 0;
        }

        Integer newStock = currMerchantStock.getStock() -1;
        Integer newBalance = currUser.getBalance() - currProduct.getPrice();

        currUser.setBalance(newBalance);
        currMerchantStock.setStock(newStock);

        return 1;
    }
    }
