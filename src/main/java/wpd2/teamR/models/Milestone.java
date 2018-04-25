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

    public Milestone(int id, String name, String desc, Timestamp dateCreated, Timestamp dateMod, Timestamp dateDue, Timestamp dateComplete)
    {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.dateCreated = dateCreated;
        this.dateModified = dateMod;
        this.dueDate = dateDue;
        this.dateCompleted = dateComplete;
    }

}
