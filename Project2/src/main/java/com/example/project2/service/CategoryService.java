package com.example.project2.service;

import com.example.project2.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public boolean addCategories(Category category){
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(category.getId())){
                return false;
            }
        }
        categories.add(category);
                return true;
    }

    public boolean updateCategories(Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(category.getId())) {
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategories(int index){
        categories.remove(index);
        return true;
    }
}
