/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 3 (Implement Prim's Algorithm)
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Runner executes a command-line interface for testing Prim's Algorithm.
 */
public class Runner {
    /** parser points to the program's instance of a GLParser.
     */
    private static GLParser parser;
    /** prim is a static reference to an instance of Prim used in this tester.
     */
    private static Prim prim; 
    
    /** welcomeMessage returns a String containing this program's welcome message.
     * 
     * @return A String containing the welcome message for this program.
     */
    private static String welcomeMessage() {
        return "Welcome to Ryan McAllister-Grum's Assignment 3 Prim's Algorithm Testing Program!";
    }
    
    public static void main(String[] args) {
        String input = ""; // input captures the user's command-line input.
        
        System.out.println(welcomeMessage()); // Output our welcome message.
        
        try (InputStreamReader reader = new InputStreamReader(System.in)) {
            char[] buff = new char[1000];
            int length;
            while(!input.equalsIgnoreCase("q")) {
                input = ""; // Reset the input buffer.
                System.out.println(); // Output a newline to ease reading error output.
                
                if (prim != null) {
                    System.out.println(prim);
                }
                
                System.out.println("Please enter in a .gl file to parse, 's' to save the output in .gl file format, or enter 'q' to quit:");
                length = reader.read(buff, 0, buff.length);
                if (length != 0)
                    input = String.copyValueOf(buff, 0, length).replace(System.lineSeparator(), "").replace("\n", "").replace("\"", "");
                
                if (!input.isBlank() && !input.isEmpty()) {
                    if (!input.equalsIgnoreCase("q") &&
                        !input.equalsIgnoreCase("s")) {
                        try {
                            prim = new Prim(new GLParser(input));
                        } catch(IllegalArgumentException | SecurityException | IOException e) {
                            System.err.println("Error while creating a new GLParser: " + e);
                        }
                    } else if (input.equalsIgnoreCase("s")) {
                        if (prim == null)
                            System.out.println("Unable to save empty MST results!");
                        else {
                            System.out.println(
                                String.format("Please enter the file name to save the output (default local path \"%s\"), or the complete file path:",
                                    System.getProperty("user.dir")
                                )
                            );

                            length = reader.read(buff, 0, buff.length);
                            if (length != 0) {
                                input = String.copyValueOf(buff, 0, length).replace("\"", "").replace(System.lineSeparator(), "").replace("\n", "");
                                Path path = Paths.get(input);
                                if (!path.isAbsolute())
                                    path = Paths.get(System.getProperty("user.dir"), path.toString());

                                // Write the mst to the given file.
                                try {
                                    prim.save(path.toString());
                                    System.out.println(String.format("MST saved to \"%s\".", path));
                                } catch (IOException e) {
                                    System.err.println("Error while saving to .gl file: " + e);
                                }
                            } else
                                System.out.println("Invalid save file or path!");
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error while executing Runner: " + e);
        }
    }
}