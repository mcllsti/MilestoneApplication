package bullitin.model; //<= CHANGE THIS PACKAGE FOR IMPORTS!!

import lombok.Data;
import wpd2.teamR.models.Project;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public @Data class User {

    private String id;
    private String fname;
    private String lname;
    private String email;
    private String password; // <= CHECK THIS - VARIABLE MUST BE KEPT SECURE
    private String salt; // THIS WILL BE NEED IN DB - NOT SURE HERE?
    private Timestamp dateCreated;
    private Date dateLoggedIn;
    private List<Project> projects;

    //private List<Project> projects; <= NAV VARIABLE? / UNSURE



    public void addProject(Project projectToAdd)
    {
        //TODO: ADD BODY
        //Function will add a project object to a user
        this.projects.add(projectToAdd);
    }


    public Project getProject(int projectToGetId)
    {
        //TODO: ADD BODY
        //gets a project using the requested Id?
        return null; // <= THIS WILL CHANGE OBVIOUSLY
    }

    public void deleteProject(int projectToDeleteId)
    {
        //TODO: ADD BODY
        //Deletes a project from a user
    }


}
