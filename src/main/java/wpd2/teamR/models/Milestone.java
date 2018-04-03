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
    private Timestamp dueCompleted;

    private int projectID; // AGAIN, I DONT THINK WE NEED THIS TO BE HONEST ?

}
