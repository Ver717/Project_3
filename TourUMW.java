import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

// okay its just the locations that arent being createed right now it ll be okay


/**
 * @author Nora Peters
 * @version 1.0
 * 
 * TourUMW
 */
public class TourUMW { // works.
    /**
     * Main class of package. Runs the tourUMW simulator and contains logic to prompt and interpret user input.
     * Is also responsible for the initialization of campus objects.
     * @param args Command line arguments. N/A
     */
    public static void main(String[] args) {
        TourStatus status = TourStatus.getInstance();
        Scanner s = new Scanner(System.in);

        System.out.println("\nWelcome to the simulator!");
        boolean quitSimulator = false;
        while (!quitSimulator) {
            Campus c = setUpCampus(s);

            if (c == null) {
                quitSimulator = true;
            } else {
                status.setCampus(c);
                status.setCurrentLocation(c.getStartingLocation());
                campusLoop(s, status);
            }
        };
        System.out.println("\n\nThank you for using the simulator! Exiting now.\n");
    }

    /**
     * This function represents the the main tour loop. It presents the user with information about the campus,
     * prompts the user for a direction to move in, and updates the users position.
     * 
     * @param s Scanner to read user input.
     * @param status TourStatus object that represents information related to the users tour.
     * Used to store and update and store users location on the tour.
     */
    private static void campusLoop(Scanner s, TourStatus status) {
        //System.out.println();
        boolean continueTour = true;
        System.out.println(status.getCurrentLocation().describeLocation());
        status.getCurrentLocation().setHaveVisited(true);
        while (continueTour) { 
            // get current location
            // uneccisary?
            //Location cLocation = status.getCurrentLocation();
  
            // print current location
            // xed out bc its going to be done through movement command
            /*String desc = "\n\n" + cLocation.getName() + "\n";
            if (cLocation.getHaveVisited() == false) {
                desc += cLocation.getDescription() + "\n";
            }
            System.out.println(desc);
            status.getCurrentLocation().setHaveVisited(true);*/

            // print directions
            // also going to be done in movement command
            //System.out.println(cLocation.describeDoors());

            // ask for direction
            // add pickup drop and backpack instructions
            System.out.print("enter a direction, or type 'quit' to exit campus: ");
            UserInputCommand command = parseInput(s.nextLine());

            // carry out user input
            if (command == null) {
                continueTour = false;
            } else {
                System.out.println(command.carryOut());
            } /*else if (!Door.isValidDir(dir)) {
                System.out.print("Error: please enter a valid direction.");
            } else {
                status.updateTourLocation(dir);
                if (status.getCurrentLocation().equals(cLocation)) { // if status couldn't update location
                    System.out.print("Error: could not move in specified direction.");
                }
            }*/
        }
        System.out.println("\n\n\nYou've exited the tour of " + status.getCampus().getCampusName() + "! ");
    }

    public static UserInputCommand parseInput(String input) {
        String in = input.trim().toUpperCase();

        String first = in.substring(0,1);
        Set<String> dirs = Set.of("N", "S", "E", "W"); 
        String[] words = in.split(" ");
        String itemName = Arrays.stream(words).skip(1).collect(Collectors.joining(" "));

        // switch?
        UserInputCommand command = null;
        if (dirs.contains(first)) {
            command = new MovementCommand(first);
        } else if (words[0].equals("PICKUP")) {
            // test if itemName exists
            command = new PickupCommand(itemName);
        } else if (words[0].equals("DROP")) {
            // test if itemName exists
            command = new DropCommand(itemName);
        } else if (words[0].equals("BACKPACK")) {
            command = new BackpackCommand();
        } else if (words[0].equals("QUIT")) {
            command = null;
        } else {
            command = new InvalidCommand(input);
        }
        
        return command;
    }

    /**
     * Function to initialize campus objects. attempts to check for valid file format by catching any exception thrown (for ex, arrayindexoutofbounds).
     * Returns null if in response to such failure, user has chosen to quit the simulator.
     * @param s Scanner to read user input
     * @return an initialized Campus object
     */
    public static Campus setUpCampus(Scanner s) { // this needs to somehow parse bad input files. its supposed to fail if format is off
        Campus c = null;
        boolean unfinishedSetup = true;
        String filename = getValidFilename(s);
        if (filename == null) { // user wants to quit
            unfinishedSetup = false;
        }
        while (unfinishedSetup) {
            try (Scanner fs = new Scanner(Path.of(filename))) {
                ArrayList<String> lines = new ArrayList<>();
                boolean isALocation = true;

                while (fs.hasNextLine()) {
                    String line = fs.nextLine().trim();

                    if (c == null) {
                        c = new Campus(line, filename); // initializes campus from name and filename
                    } else if (line.equalsIgnoreCase("Doors:")) {
                        isALocation = false;
                    } else if (!line.contains("*****") && !line.contains("+++") && !line.endsWith(":")) { // line containing informaton about an object
                        lines.add(line);
                        //System.out.println(line);
                    } else if (lines.size() != 0){ // line must be a +++, delimiting end of object to-be
                        
                        if (isALocation) { // is end of a location
                            String name = lines.get(0);
                            lines.removeFirst();
                            String desc = String.join(" ", lines);

                            Location l = new Location(name, desc);
                            if (c.getStartingLocation().getName().equalsIgnoreCase("")) { // adds starting location
                                c.setStartingLocation(l); 
                            }
                            c.addLocation(l); // adds location 
                        } else { // is end of a door
                            String dir = lines.get(1).toUpperCase().substring(0,1);
                            String enter = lines.get(2);
                            String leave = lines.get(0);

                            Location leaveLoc = c.getLocation(leave);
                            Location enterLoc = c.getLocation(enter);
                            Door d = new Door(dir, leaveLoc, enterLoc); // leaving door, points away from 

                            c.getLocation(leave).addDoor(d); // adds door to location: door points outward from its location
                        }
                        lines.clear(); // cleans up for next object
                    }
                    // tests to see if file was valid; doors need to be connected properly for functionality
                    
                }
                unfinishedSetup = false;
            } catch(Exception e) { // catches any exceptions that occur when reading file. exception thrown implies invalid format, but not all problems with invalid format may be caught.
                System.out.println("Error: " + filename + " is not correctly formatted.");
                String newFilename = getValidFilename(s);
                
                if (newFilename == null) { // user chose to quit
                    unfinishedSetup = false; 
                } 
                c = null; // clears any bad data and if necissary allows simulator loop to quit   
                
            }

        }
        return c;
    }

    /**
     * Helper method for setUpCampus that gets a input filename from the user, and tests to make sure it exits in the file system. 
     * Seperates file validation logic from input handling logic.
     * 
     * @param s Scanner to read user input: Scanner
     * @return Name of an existant file, or null if user chooses to quit
     */
    private static String getValidFilename(Scanner s) {
        String filename = null;
        boolean invalidPath = true;
        while (invalidPath) {
            System.out.print("\nEnter the name of an input file to begin a new campus tour, or 'q' to exit the simulator: ");
            filename = s.nextLine().trim();
            Path p = Path.of(filename);

            if (!Files.isRegularFile(p) && !filename.substring(0,1).equalsIgnoreCase("q")) {
                System.out.println("Error: invalid path.");
            } else {
                invalidPath = false;
            }
            if (filename.equalsIgnoreCase("q")) {
                filename = null;
            }
        }

        return filename;
    }
}
