package task;

import java.time.LocalDateTime;
import java.util.List;

public class Category extends Entity {
    private String name;
    public static List<String> categories;

    public Category() {
    }

    public Category(String name) {
        super(LocalDateTime.now());
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String categoryName) {
        this.name = categoryName;
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
                "categoryName='" + name + '\'' +
                '}';
    }
}
