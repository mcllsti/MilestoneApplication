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

    public Milestone(){
    }

    public Milestone(int id, String name, String description, Timestamp dateCreated, Timestamp dateModified, Timestamp dueDate, Timestamp dateCompleted, int projectID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.dueDate = dueDate;
        this.dateCompleted = dateCompleted;
        this.projectID = projectID;
        
    }



}
