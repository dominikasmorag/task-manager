package task;

import java.time.LocalDateTime;
import java.util.List;

public class Category extends Entity {
    private String categoryName;
    public static List<String> categories;


    public Category() {
    }

    public Category(String categoryName) {
        super(LocalDateTime.now());
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static List<String> getCategories() {
        return categories;
    }

    public static void setCategories(List<String> categories) {
        Category.categories = categories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
