package ebulton.BeLive;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import ebulton.BeLive.user.User;
import ebulton.BeLive.DataStructs.Dictionary;
import ebulton.BeLive.treatments.Database;
import ebulton.BeLive.treatments.Treatment;

/**
 * (PROTOTYPE)
 * This class is created to help people who were
 * born into difficult environments and lack the resources
 * to live and recover from medical related injuries
 * that they face. 
 * This application will allow the user to select the
 * spot in the body where the injury is and provide the
 * user with the most convenient remedy for the user
 * that they will be able to find in their area.
 * This application may also include the ability to
 * photograph the injury if not within database and
 * immediately send it to professionals allowing them
 * to provide instructions on what to do next.
 * This application will be provided to people in
 * difficult to survive regions or circumstances
 * within their area limit them from reaching out
 * to help from doctors in their area.
 * This application will possibly be provided with
 * medical kits and on hand remedies to common injuries.
 * In special cases or maybe in the future this program
 * will be provided with a body case that will scan the
 * body and provide accurate results.
 * @author ElstanB
 */
public class BeLive {
    
    /** The user that will be using the BeLive application */
    private User user;
    
    /** The body part where the injury is 1(Head\Neck) 2(Chest) 3(Arm) 5(Wrist/Hand) 4(Leg) 5(Foot/Ankle) */
    private int bodyPart;
    
    /** The orientation of the body part where the injury is 0 for both 1 for front and 2 for back */
    private int orientation;
    
    /** The description of the injury provided by the user */
    private String injuryDescription;

    /** The symptoms provided by the user */
    private String symptoms;
    
    /** The database for where the treatments will be */
    private Database database;
    
    /** The dictionary to find all the treatments */
    private Dictionary dictionary;
    
    /**
     * ##TO-DO: Find a way so that the database does not have to be constructed
     * everytime the application is run and instead the data will be saved when 
     * the application is closed and will be saved when the application is opened 
     * again so there is no need to waste time recreating the database everytime 
     * the application is run.
     */
    
    /** The treatment that will be found by the BeLive application */
    private String[][] treatments;
    
    /**
     * NEW USER CONSTRUCTOR
     * Constructor for the BeLive application that sets the user and the bodypart given by the GUI
     * @param user the user of the application
     * @param bodypart the bodypart that the user needs treatment for
     */
    public BeLive(User user, int bodyPart, int orientation, String injuryDescription, String symptoms){
        database = new Database("/files/treatments.txt");
        dictionary = new Dictionary(database);
        setUser(user);
        setBodypart(bodyPart, orientation);
        setInjuryDescription(injuryDescription);
        setSymptoms(symptoms);
        
    }
    
    /**
     * RETURNING USER CONSTRUCTOR
     * Constructor for the BeLive application in the case of a returning user
     * @param firstName the first name of the returning user to find
     */
    public BeLive(String firstName, String lastName) {
    	database = new Database("/files/treatments.txt");
        dictionary = new Dictionary(database);
    	user = findUser(firstName, lastName);
    }
    
    /**
     * Searches for the treatment in the database based on the body part that
     * that needs treatment then the location of the user
     * @return an array containing the treatment that is available
     */
    public String[][] findTreatment(){    	
    	//The size of all the treatments
    	int size = dictionary.getAll(bodyPart).size();
    	
    	//Array of Strings containing the treatment
    	String[][] treatmentsWithNullCells = new String[size][5];
    	
    	//Index in treatment array
    	int j = 0;
    	
    	//The linked list containing the treatments found
    	LinkedList<Treatment> found = dictionary.get(bodyPart, orientation, symptoms, injuryDescription);
    	
    	//GET BETTER IMPLEMENTATION OF MULTIPLE SYMPTOMS IN A COMMA SEPARATED LIST
    	
    	//As long is some treatments were found
    	if(found != null) {
    		Iterator<Treatment> it = found.iterator();
	    	while(it.hasNext()) {
	    		Treatment t = it.next();
	    		treatmentsWithNullCells[j][0] = t.getName();
	    		treatmentsWithNullCells[j][1] = t.getDescription();
	    		treatmentsWithNullCells[j][2] = t.getUses();
	    		treatmentsWithNullCells[j][3] = t.getLocation();
	    		treatmentsWithNullCells[j][4] = t.getSymptoms();
	    		j++;
	    	}
    	}
    	else {
    		found = dictionary.getAll(bodyPart);
    		Iterator<Treatment> it = found.iterator();
	    	while(it.hasNext()) {
	    		Treatment t = it.next();
	    		treatmentsWithNullCells[j][0] = t.getName();
	    		treatmentsWithNullCells[j][1] = t.getDescription();
	    		treatmentsWithNullCells[j][2] = t.getUses();
	    		treatmentsWithNullCells[j][3] = t.getLocation();
	    		treatmentsWithNullCells[j][4] = t.getSymptoms();
	    		j++;
	    	}
    	}
    	
    	treatments = new String[j][5];
    	for(int i = 0; i < j; i++) {
    		treatments[i][0] = treatmentsWithNullCells[i][0];
    		treatments[i][1] = treatmentsWithNullCells[i][1];
    		treatments[i][2] = treatmentsWithNullCells[i][2];
    		treatments[i][3] = treatmentsWithNullCells[i][3];
    		treatments[i][4] = treatmentsWithNullCells[i][4];
    	}
    	
		return treatments;
    }
    
//    private boolean containUses(String databaseUses) {
////    	System.out.println("Searching");
//		boolean contains = false;
//    	
//    	Scanner s = new Scanner(databaseUses);
//    	
//		s.useDelimiter(",");
//		
//		while(s.hasNext()) {
//			if(injuryDescription.contains(s.next())) {
//				contains =  true;
//				System.out.println("Found Treatment");
//			}
//		}
//		
//		s.close();
//		
//    	return contains;
//    }
    
    /**
     * Searches the database for the returning user based on first name
     * @return the user with the same first name
     * @throws NullPointerException if the firstName or lastName is null
     * @throws IllegalArgumentException if the firstName or lastName is an empty String
     * @throw IllegalArgument Exception if the user based on the first name or last name is not found
     */
    public User findUser(String firstName, String lastName) {
    	User found = null;
    	
    	if(firstName == null) {
    		throw new NullPointerException();
    	}
    	if("".equals(firstName)) {
    		throw new IllegalArgumentException("First name cannot be an empty String.");
    	}
    	
    	if(lastName == null) {
    		throw new NullPointerException();
    	}
    	if("".equals(lastName)) {
    		throw new IllegalArgumentException("First name cannot be an empty String.");
    	}
    	
    	for(int i = 0; i < database.getUsers().size(); i++) {
    		if(database.getUsers().get(i).getFirstName().equals(firstName) 
				&& database.getUsers().get(i).getLastName().equals(lastName)) {
    			found = database.getUsers().get(i);
    		}
    	}
    	
    	if(found == null) {
    		throw new IllegalArgumentException("There is no user with that first name.");
    	}
    	
    	return found;
    }

	/**
	 * Returns the user of the application
	 * @return the user of the application
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user of the application
	 * @param user the user to set
	 * @throws NullPointerException if the user is null
	 */
	public void setUser(User user) {
		if(user == null) {
			throw new NullPointerException("User cannot be null.");
		}
		this.user = user;
		//Store the user to the database
		if(!(user.isEdited())) {
			database.storeUser(user);
		}
	}
	
	/**
	 * Edits the current users information and edits the information in the database
	 * @param user the edited user to store
	 * @throws NullPointerException if the edited user is null
	 */
	public void editUser(User user) {
		if(user == null) {
			throw new NullPointerException("User cannot be null.");
		}
		this.user = user;
		database.editUser(user);
	}

	/**
	 * Returns the body part the user needs treatment for
	 * @return the bodyPart
	 */
	public int getBodypart() {
		return bodyPart;
	}
	
	/**
	 * Returns the orientation of the body part injured
	 * @return the orientation of the body part injured
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * Sets the body part that the user needs treatment for
	 * @param bodyPart the bodyPart to set
	 * @param orientation the orientation of the body part to set
	 * @throws IllegalArgumentException if body part is below 1
	 * or greater than 5 or orientation is less than 0 or greater than 2
	 */
	public void setBodypart(int bodyPart, int orientation) {
		if(bodyPart < 1 || bodyPart > 6) {
			throw new IllegalArgumentException("Invalid input for body part.");
		}
		if(orientation < 0 || orientation > 2) {
			throw new IllegalArgumentException("Invalid input for orientation.");
		}
		this.bodyPart = bodyPart;
		this.orientation = orientation;
	}

	/**
	 * Returns the description of the injury provided by the user
	 * @return the injuryDescription from the user
	 */
	public String getInjuryDescription() {
		return injuryDescription;
	}

	/**
	 * Sets the description of the injury from the user
	 * @param injuryDescription the injuryDescription to set
	 * @throws NullPointerException if the description of the injury is null
	 * @throws IllegalArgumentException if the description of the injury is an empty String
	 */
	public void setInjuryDescription(String injuryDescription) {
		if(injuryDescription == null) {
			throw new NullPointerException("The description of the injury cannot be null");
		}
		if("".equals(injuryDescription)) {
			throw new IllegalArgumentException("The description of the injury cannot be empty.");
		}
		this.injuryDescription = injuryDescription;
	}

	/**
	 * Returns the symptoms of the user
	 * @return the symptoms of the user
	 */
	public String getSymptoms() {
		return symptoms;
	}

	/**
	 * Sets the symptoms of the user
	 * @param symptoms the symptoms of the user to set
	 */
	public void setSymptoms(String symptoms) {
		if(injuryDescription == null) {
			throw new NullPointerException("The symptoms of the injury cannot be null");
		}
		if("".equals(injuryDescription)) {
			throw new IllegalArgumentException("The symptoms of the injury cannot be empty.");
		}
		this.symptoms = symptoms;
	}
    
    
}