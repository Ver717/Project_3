import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

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
        TourStatus status = new TourStatus();
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
        System.out.println("Thank you for using the simulator! Exiting now.");
    }

    /**
     * Function to initialize campus objects. attempts to check for valid file format by catching any exception thrown (for ex, arrayindexoutofbounds).
     * Returns null if in response to such failure, user has chosen to quit the simulator.
     * @param s Scanner to read user input
     * @return an initialized Campus object
     */
    public static Campus setUpCampus(Scanner s) { // this needs to somehow parse bad input files. its supposed to fail if format is off
        Campus c = null;
        String filename = getValidFilename(s);

        while (filename != null) {
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
                        //System.out.println()
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
                            //Location leaveLoc = c.getLocations().get(leave);
                            //Location enterLoc = c.getLocations().get(enter);
                            Door d = new Door(dir, leaveLoc, enterLoc); // leaving door, points away from 

                            //c.getLocations().get(leave).addDoor(d); // adds door to location: door points outward from its location
                            c.getLocation(leave).addDoor(d);
                        }
                        lines.clear(); // cleans up for next object
                    }
                    // tests to see if file was valid; doors need to be connected properly for functionality
                    System.out.println("f");
                }
                filename = null;
            } catch(Exception e) { // catches any exceptions that occur when reading file. exception thrown implies invalid format, but not all problems with invalid format may be caught.
               System.out.println("g");
                /*System.out.println("Error: critical file error. If you wish to continue, enter a different filename.");
                String newFilename = getValidFilename(s);
                if (newFilename == null) {
                    filename = null; // quits setupCampus loop
                } else {
                    filename = newFilename;
                }
                c = null; */// clears any bad data and allows simulator loop to quit   
                System.out.println("excepion reachedd");
            }

        }
        System.out.println("h");
        return c;
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
        System.out.println();
        boolean continueTour = true;
        while (continueTour) { 
            // get current location
            Location cLocation = status.getCurrentLocation();
  
            // print current location
            String desc = "\n\n" + cLocation.getName() + "\n";
            if (cLocation.getHaveVisited() == false) {
                desc += cLocation.getDescription() + "\n";
            }
            System.out.println(desc);
            status.getCurrentLocation().setHaveVisited(true);

            // print directions
            System.out.println(cLocation.describeDoors());

            // ask for direction
            System.out.print("enter a direction, or type 'q' to exit campus: ");
            String dir = s.nextLine().trim();

            // move user
            if (dir.substring(0,1).equalsIgnoreCase("q")) {
                continueTour = false;
            } else if (!Door.isValidDir(dir)) {
                System.out.print("\nError: please enter a valid direction.");
            } else {
                status.updateTourLocation(dir);
                if (status.getCurrentLocation().equals(cLocation)) { // if status couldn't update location
                    System.out.print("\nError: could not move in specified direction.");
                }
            }
        }
        System.out.println("\n\n\nYou've exited the tour of " + status.getCampus().getCampusName() + "! ");
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
