package wpd2.teamR.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Milestone {

    private int id;
    private String name;
    private String description;
    private Timestamp dateCreated;
    private Timestamp dateModified;
    private Timestamp dueDate;
    private Timestamp dateCompleted;

    private int projectID; // AGAIN, I DONT THINK WE NEED THIS TO BE HONEST ?

    public Milestone(int id, String name, String description, Timestamp dateCreated, Timestamp dateModified, Timestamp dueDate, Timestamp dateCompleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.dueDate = dueDate;
        this.dateCompleted = dateCompleted;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Timestamp dateCompleted) {
        this.dateCompleted = dateCompleted;
    }


}
