package wpd2.teamR.models;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Link {

    private int id;
    private String email;
    private Timestamp dateCreated;
    private Timestamp dateLastAccessed;
    private int projectID; // DON'T KNOW IF WE NEED THIS? PROJECT BELOW MIGHT BE FINE
    private Project project;

}