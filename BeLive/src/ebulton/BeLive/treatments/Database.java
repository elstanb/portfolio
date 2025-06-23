package ebulton.BeLive.treatments;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
// import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ebulton.BeLive.user.User;

/**
 * This class constructs the Database object based on a text file that will be used to store all of the treatments 
 * and previous users
 * @author ElstanB
 */
public class Database {
	
	/**
	 * HOW THE TREATMENT DATA IS DIVIDED
	 * NAME |BODYPART |ORIENTATION |USES |DESCRIPTION |LOCATION-TO-FIND 
	 */
    
    /** The ArrayList containing all of the treatments */
    private ArrayList<Treatment> treatments;
    
    /** The ArrayList of users that have used the application */
    private ArrayList<User> users;
    
    /** The filename used to store the previous users */
    private static String USERS_FILE = "C:\\iData\\Code\\portfolio\\BeLive\\files\\users.txt";
    
    /** THESE COUNTERS MAY NOT BE NEEDED BUT ARE ADDED JUST AS USEFUL DATA */
    /** Counter for Head/Neck related treatments */
    private int counterPart1 = 0;
    
    /** Counter for Chest related treatments */
    private int counterPart2 = 0;
    
    /** Counter for Arm related treatments */
    private int counterPart3 = 0;
    
    /** Counter for Hand/Wrist related treatments */
    private int counterPart4 = 0;
    
    /** Counter for Leg related treatments */
    private int counterPart5 = 0;
    
    /** Counter for Foot/Ankle related treatments */
    private int counterPart6 = 0;
    
    /**
     * Constructor for the Database object based on the name of the file entered
     * @param fileName the name of the file
     */
	public Database(String fileName){
    	users = new ArrayList<User>();
    	readUsers();
    	treatments = new ArrayList<Treatment>();
    	
		//Create reference to individual treatments
		Treatment treatment;
		
		//String containing the entire file
		String s = "";
		
		//Create Scanner
		Scanner fileReader;
		
		try {
				fileReader = new Scanner(new FileInputStream(fileName));
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Invalid database file.");
			}
		
		while(fileReader.hasNextLine()) {
			try {
				s += fileReader.nextLine() + "\n";
			}
			catch(Exception e) {
				//Do nothing with exceptions
			}
		}
		fileReader.close();

		Scanner lineReader = new Scanner(s);
		lineReader.useDelimiter("\\n");
		
		while(lineReader.hasNext()) {
			try {
				treatment = processTreatments(lineReader.next());
				treatments.add(treatment);
			}
			catch (Exception e) {
				
			}
		}
		lineReader.close();
    	
    	// Treatment a = new Treatment("country1", "treatment1", "use1", "treatment1 description", "location1", "symptom1", 4, 0);
    	// Treatment b = new Treatment("country1", "treatment2", "use2", "treatment2 description", "location2", "symptom2", 4, 0);
    	// Treatment c = new Treatment("country1", "treatment3", "use3", "treatment3 description", "location3", "symptom3", 4, 0);
    	// Treatment d = new Treatment("country1", "treatment4", "use4", "treatment4 description", "location4", "symptom4", 4, 0);
    	// Treatment e = new Treatment("country1", "treatment5", "use5", "treatment5 description", "location5", "symptom5", 4, 0);
    	// Treatment f = new Treatment("country1", "treatment6", "use6", "treatment6 description", "location6", "symptom6", 4, 0);
    	// Treatment g = new Treatment("country1", "treatment7", "use7", "treatment7 description", "location7", "symptom7", 4, 0);
    	// Treatment h = new Treatment("country1", "treatment8", "use8", "treatment8 description", "location8", "symptom8", 4, 0);
    	// Treatment i = new Treatment("country1", "treatment9", "use9", "treatment9 description", "location9", "symptom9", 4, 0);
    	// Treatment j = new Treatment("country1", "treatment10", "use10", "treatment10 description", "location10", "symptom10", 4, 0);
    	// Treatment k = new Treatment("country1", "treatment11", "use11", "treatment11 description", "location11", "symptom11", 4, 0);
    	// Treatment l = new Treatment("country1", "treatment12", "use12", "treatment12 description", "location12", "symptom12", 4, 0);
    	// Treatment m = new Treatment("country1", "treatment13", "use13", "treatment13 description", "location13", "symptom13", 4, 0);
    	// Treatment n = new Treatment("country1", "treatment14", "use14", "treatment14 description", "location14", "symptom14", 4, 0);
    	// Treatment o = new Treatment("country1", "treatment15", "use15", "treatment15 description", "location15", "symptom15", 4, 0);
    	// Treatment p = new Treatment("country1", "treatment16", "use16", "treatment16 description", "location16", "symptom16", 4, 0);
    	// Treatment q = new Treatment("country1", "treatment17", "use17", "treatment17 description", "location17", "symptom17", 4, 0);
    	// Treatment r = new Treatment("country1", "treatment18", "use18", "treatment18 description", "location18", "symptom18", 4, 0);
        // treatments.add(a);
        // treatments.add(b);
        // treatments.add(c);
        // treatments.add(d);
        // treatments.add(e);
        // treatments.add(f);
        // treatments.add(g);
        // treatments.add(h);
        // treatments.add(i);
        // treatments.add(j);
        // treatments.add(k);
        // treatments.add(l);
        // treatments.add(m);
        // treatments.add(n);
        // treatments.add(o);
        // treatments.add(p);
        // treatments.add(q);
        // treatments.add(r);
    }
    
	/**
	 * Private method that reads the user file when the database is created so the 
	 * database contains all the previous users that are stored with the storeUser method
	 * @throws IllegalArgumentException if the file is unable to be read
	 */
	private void readUsers() {
		/** The user that will recieved from the file */
		User user;
		
		/** The string containing all of the file contents */
		String s = "";
		
		/** The scanner that will read the file */
		Scanner fileReader;
		
		try {
			fileReader = new Scanner(new FileInputStream(USERS_FILE));
			
		}
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file.");
		}
		
		while(fileReader.hasNextLine()) {
			try {
				s += fileReader.nextLine() + "\n";
			}
			catch(Exception e) {
				//No element was found ignore file
			}
		}
		
		//New Scanner that will read the lines
		Scanner lineReader = new Scanner(s);
		
		lineReader.useDelimiter("\\n");
		
		while(lineReader.hasNext()) {
			try {
				user = processUsers(lineReader.next());
				users.add(user);
			}
			catch(Exception e) {
				//Do nothing if no user is found
			}
		}

		lineReader.close();
		fileReader.close();
	}
	
	/**
	 * Private helper method for the readUsers method
	 * @param s the line of the file to process
	 * @return user the user from the file
	 */
	private User processUsers(String s) {
		//Initialize variables
		User user;
		String firstName = "";
		String lastName = "";
		boolean gender = false;
		int age = 0;
		int height = 0;
		int weight = 0;
		String location = "";
		
		//Create scanner for users
		Scanner userReader = new Scanner(s);
		
		//User delimiter to separate elements
		userReader.useDelimiter(", ");
		
		//Set variables to values from file
		while(userReader.hasNext()) {
			firstName = userReader.next();
			lastName = userReader.next();
			gender = userReader.nextBoolean();
			age = userReader.nextInt();
			height = userReader.nextInt();
			weight = userReader.nextInt();
			location = userReader.next();
		}
		
		user = new User(firstName, lastName, gender, age, height, weight, location);
		userReader.close();
		
		return user;
		
	}
	
	private Treatment processTreatments(String s) {
		Treatment processed = null;
		
		Scanner treatmentReader = new Scanner(s);
		
		treatmentReader.useDelimiter(",");
		
		String country = "";
		String name = "";
		String uses = "";
		String description = "";
		String location = "";
		String symptom = "";
		int bodyPart = 0;
		int orientation = 0;
		
		try {
			country = treatmentReader.next().trim();
			name = treatmentReader.next().trim();
			uses = treatmentReader.next().trim();
			description = treatmentReader.next().substring(1);
			location = treatmentReader.next().trim();
			symptom = treatmentReader.next().trim();
			bodyPart = Integer.parseInt(treatmentReader.next().trim());
			if(bodyPart == 1){
				counterPart1++;
			}
			else if(bodyPart == 2){
				counterPart2++;
			}
			else if(bodyPart == 3){
				counterPart3++;
			}
			else if(bodyPart == 4){
				counterPart4++;
			}
			else if(bodyPart == 5){
				counterPart5++;
			}
			else if(bodyPart == 6){
				counterPart6++;
			}
			orientation = Integer.parseInt(treatmentReader.next().trim());
			treatmentReader.close();
		}
		catch(NoSuchElementException e) {
			throw new IllegalArgumentException("Invalid treatment.");
		}
		
		
		try {
			processed = new Treatment(country, name, uses, description, location, symptom, bodyPart, orientation);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Invalid treatment.");
		}
		
		return processed;
		
	}
	
    /**
     * Stores the user to database based on prior usage
     * @param store the user to store
     * @throws IllegalArgumentException if the file is unable to be saved
     */
    public void storeUser(User store) {
    	//Check for duplicate users
    	for(int i = 0; i < users.size(); i++) {
    		if(users.get(i).getFirstName().equals(store.getFirstName()) 
				&& users.get(i).getLastName().equals(store.getLastName())
				&& users.get(i).getAge() == store.getAge()
				&& users.get(i).getHeight() == store.getHeight()
				&& users.get(i).getWeight() == store.getWeight()
				&& users.get(i).isGender() == store.isGender()
				&& users.get(i).getCountry().equals(store.getCountry())) {
    			throw new IllegalArgumentException("Unable to store duplicate user.");
    		}
    	}
    	
    	//Add the user if there are not duplicates
    	users.add(store);
    	
    	//Stores the user based on the call from the BeLive class
    	try {
    		PrintStream fileWriter = new PrintStream(new File(USERS_FILE));
    		String s = "";
    		for(User user : users) {
    			s += user.toString() + "\n";
    		}
    		fileWriter.print(s);
    		fileWriter.close();
    		System.out.println("Writing to the users file");
    	}
    	catch(Exception e) {
    		throw new IllegalArgumentException("Unable to save file");
    	}
    }
    
    /**
     * Edits and stores the user in the database
     */
    public void editUser(User user) {
    	boolean notEdited = false;
    	
    	for(int i = 0; i < users.size(); i++) {
    		if(users.get(i).getFirstName().equals(user.getFirstName())
    				&& users.get(i).getLastName().equals(user.getLastName())
    				&& users.get(i).isGender() == user.isGender()
    						|| ((users.get(i).getAge() != user.getAge()) 
    						|| (users.get(i).getHeight() != user.getHeight()) 
    						|| (users.get(i).getWeight() != user.getWeight()))) {
    			users.remove(i);
    		}
    		else {
    			notEdited = true;
    		}
    	}
    	if(!notEdited) {
        	storeUser(user);
    	}
    }
    
	/**
	 * Returns all the treatments
	 * @return the treatments
	 */
	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}

	/**
	 * Returns all the previous users
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Return counter for the number of head/neck treatments
	 * @return the counterPart1
	 */
	public int getCounterPart1() {
		return counterPart1;
	}

	/**
	 * Return counter for the number of chest treatments
	 * @return the counterPart2
	 */
	public int getCounterPart2() {
		return counterPart2;
	}

	/**
	 * Return counter for the number of arm treatments
	 * @return the counterPart3
	 */
	public int getCounterPart3() {
		return counterPart3;
	}

	/**
	 * Return counter for the number of hand/wrist treatments
	 * @return the counterPart4
	 */
	public int getCounterPart4() {
		return counterPart4;
	}

	/**
	 * Return counter for the number of leg treatments
	 * @return the counterPart5
	 */
	public int getCounterPart5() {
		return counterPart5;
	}

	/**
	 * Return counter for the number of foot/ankle treatments
	 * @return the counterPart6
	 */
	public int getCounterPart6() {
		return counterPart6;
	}
    
}