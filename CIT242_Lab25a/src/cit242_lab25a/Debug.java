
/*
 * Debug methods for "verbose" logging
 */
package cit242_lab25a;

public class Debug {
    private static boolean verboseMode = false; // Debug aid (false by default)
    //________________________________________________________________________________________
    /**
     *
     * @return the current value of the Boolean 'verboseMode' attribute.
     */
    public static Boolean getVerboseMode() {
        return verboseMode;
    }

    //________________________________________________________________________________________
    /**
     *
     * @param value = new value for the Boolean 'verboseMode' attribute.
     */
    public static void setVerboseMode(Boolean value) {
        verboseMode = value;
    }

    //________________________________________________________________________________________
    /**
     * Turn "verbose mode" ON (true) or OFF (false).
     */
    public static void setOrClearVerboseMode() {
        verboseMode = !verboseMode;
    }
}
