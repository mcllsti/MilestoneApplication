package wpd2.teamR.util;

import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionFunctions {

    // CONSTANTS FOR USE IN UTILITY
    static final String FLASH_MESSAGE_KEY = "flashMessage";

    /**
     * Save a flash message to the session
     * @param request The current request
     * @param message The message to write to the session
     */
    public static void setFlashMessage(HttpServletRequest request, FlashMessage message) {
        HttpSession session = request.getSession(true);
        session.setAttribute(FLASH_MESSAGE_KEY, message);
    }

    /**
     * Retrieve a flash message from the session
     * @param request The current request
     * @return The flash message stored in the session, otherwise return a blank
     */
    public static FlashMessage getFlashMessage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new FlashMessage(FlashMessage.FlashType.BLANK,"","");
        }
        FlashMessage message = (FlashMessage)session.getAttribute(FLASH_MESSAGE_KEY);

        // CLEAR IT BECAUSE ITS BEEN RETRIEVED
        clearFlashMessage(request);

        return (message == null ? new FlashMessage(FlashMessage.FlashType.BLANK,"") : message);
    }

    /**
     * Clear the message currently stored in the session
     * @param request The current request
     */
    public static void clearFlashMessage(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(FLASH_MESSAGE_KEY);
    }

}
