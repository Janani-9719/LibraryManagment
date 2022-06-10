package lk.ijse.LibraryManagement.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private int categoryId;
    private String category;

    public  CategoryDTO(){

    }

    public CategoryDTO(int categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                '}';
    }
}
