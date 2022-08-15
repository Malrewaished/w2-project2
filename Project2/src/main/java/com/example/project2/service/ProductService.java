package com.example.project2.service;
import com.example.project2.model.MerchantStock;
import com.example.project2.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ArrayList<Product> products = new ArrayList<>();


    public ArrayList<Product> getProducts(){
        return products;
    }

    public boolean addProduct(Product product){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                return false;
            }
        }
            products.add(product);
            return true;
    }
    public boolean updateProduct(Product product){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }
    public boolean deleteProduct(int index){
                products.remove(index);
                return true;
            }

    public Product getProduct(String productID){
        for (Product product : products)
        {
            if(product.getId().equals(productID)){
                return product;
            }
        }
        return null;
    }
}