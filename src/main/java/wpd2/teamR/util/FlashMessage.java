package wpd2.teamR.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FlashMessage {

    @Setter
    private FlashType type;
    private String heading;
    private String message;


    /**
     * Construct a flash message
     *
     * @param type    Message type
     * @param heading Message heading
     * @param message Messing to display
     */
    public FlashMessage(FlashType type, String heading, String message) {
        this.type = type;
        this.heading = heading;
        this.message = message;
    }

    /**
     * Overloaded constructor
     *
     * @param type    Message type
     * @param message Message
     */
    public FlashMessage(FlashType type, String message) {
        this.type = type;
        this.message = message;
        this.heading = ""; // SET AS BLANK FOR NOW
    }

    /**
     * Get type for building CSS classes - Overriding LOMBOK
     *
     * @return Lowercase string representation of Type ENUM
     */
    public String getType() {
        return this.type.toString().toLowerCase();
    }

    /**
     * ENUM TO HOLD MESSAGE TYPE
     */
    public enum FlashType {
        SUCCESS, WARNING, ERROR, INFO, BLANK
    }

    ;

}


