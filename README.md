# Milestone Application #

This will be the repo for our WPD2 Coursework.
The application itself is a milestone application that allows users to have different projects that are made up of milestones. Users can only see their own projects and must log in to do so but can share them if they wish. 

## GROUP ID
```
GROUP ID: R
```

## Team Members
Below are the current team members

|Team Member |	Student ID	|	Email Address|
| --- | :---: | ---: |
|Christopher Connor	|	S1715477	|	Cconno208@caledonian.ac.uk|
|Daryl McAllister	|	S1222204	|	Dmcall200@caledonian.ac.uk|
|Gavin McLeod	|	S1715408	|	Gmacle204@caledonian.ac.uk|
|Niklas Olsson	|	S1434184	|	Nolsso200@caledonian.ac.uk|

## Completed Progress
The system has basic implementation and a working servlet that allows the return of a static web page. There is currently some placeholder models folder that contains skeleton initial models that will be implemented into the project. 
There is also the start of mustache implementation but it is basic and is not intergrated with the rest of the project. 

## Current In Progress

- Finlize Project Initialization
- Mustache intergration

# Internal Feature Help
Below are a list of instructions for the team on how to use any of the custom implemented code.

## FLASH MESSAGING
Flash messaging is a feature which allows you to write custom messages to the session which can be displayed and then wiped from the session straight away. This helps for sending and rendering messages across components / views.

### Message object
Message object looks like this
```java
public class FlashMessage {

    @Setter private FlashType type;
    private String heading;
    private String message;
    
}
```
`FlashType` has the following values which can be used:
```java
public enum FlashType {SUCCESS,WARNING,ERROR,INFO,BLANK};
```
### Writing a Flash Message to session
Writing a message to session is achieved like this by passing the `HttpServletRequest` as param 1, and `FlashMessage` object as param 2.
```java
SessionFunctions.setFlashMessage(request,new FlashMessage(FlashMessage.FlashType.SUCCESS,"Successfully logged in","Welcome back :)"));
```

### Retrieving a Flash Message
To retrieve, call a similar method like this:
```java
FlashMessage message = SessionFunctions.getFlashMessage(request);
```

### Mustache Partial
Whenever you want to render this message out in a view, make sure a following partial exists in the mustache template
```
{{>_partials/flash-message}}
```