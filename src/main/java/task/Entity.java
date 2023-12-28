package task;

import java.sql.Timestamp;

public class Entity {

    private int id;
    private Timestamp creationDate;

    public Entity(){}

    public Entity(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
    public Entity(int id, Timestamp creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
