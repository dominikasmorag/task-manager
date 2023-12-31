package task;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class CategoryEntity extends Entity {
    private String name;
    public static List<String> categories;

    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        super(Timestamp.valueOf(LocalDateTime.now()));
        this.name = name;
    }


    public CategoryEntity(int id, String name, Timestamp creationDate) {
        super(id, creationDate);
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
        CategoryEntity.categories = categories;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "categoryName='" + name + '\'' +
                '}';
    }
}
