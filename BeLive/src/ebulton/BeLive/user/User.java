package ebulton.BeLive.user;
/** 
 * This class creates the user object of the BeLive application
 * @author ElstanB
*/

public class User{
    
    /** The weight of the user */
    private int weight;
    
    /** The height of the user */
    private int height;
    
    /** The gender of the user false = men, true = women */
    private boolean gender;
    
    /** The country of the user */
    private String country;
    
    /** The first name of the user */
    private String firstName;
    
    /** The last name of the user */
    private String lastName;
    
    /** The age of the user */
    private int age;
    
    /** Whether or not the user is edited */
    private boolean edited;
    
    /**
	 * Constuctor for user object
	 * @param fName the user's first name
	 * @param lName the user's last name
	 * @param gender the user's gender Male = False Female = True
	 * @param age the user's age
	 * @param height the user's height
	 * @param weight the user's weight
	 * @param location the location where the user is
	 */
    public User(String fName, String lName, boolean gender, int age, int height, int weight, String location){
        //Initialize all private fields
        this.setFirstName(fName);
        this.setLastName(lName);
        this.setGender(gender);
        this.setAge(age);
        this.setHeight(height);
        this.setWeight(weight);
        this.setCountry("country1");
        
    }


	/**
	 * Returns the weight of the user
	 * @return the weight of the user
	 */
	public int getWeight() {
		return weight;
	}


	/**
	 * Sets the weight of the user
	 * @param weight the weight to set
	 * @throws IllegalArgumentException if the weight
	 * of the user is below 0 or over 400 subject to change
	 */
	public void setWeight(int weight) {
		if(weight < 0 || weight > 400 /** Not sure what to put here just put an semi reasonable number */) {
			throw new IllegalArgumentException("Invalid Weight.");
		}
		this.weight = weight;
	}


	/**
	 * Returns the height of the user
	 * @return the height of the user
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * Sets the height of the user
	 * @param height the height to set
	 * @throws IllegalArgumentException if the height
	 * of the user is below 0 or over 107 in. subject to change
	 */
	public void setHeight(int height) {
		if(height < 0 || height > 107 /** Height of tallest person in the world can change later */) {
			throw new IllegalArgumentException("Invalid Height.");
		}
		this.height = height;
	}


	/**
	 * Returns the gender of the user
	 * @return the gender of the user
	 */
	public boolean isGender() {
		return gender;
	}


	/**
	 * Sets the gender of the user
	 * @param gender the gender to set
	 */
	private void setGender(boolean gender) {
		this.gender = gender;
	}


	/**
	 * TEMPORARY will change when location services are added
	 * Gets the country of the user
	 * @return the country of the user
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * TEMPORARY will change when location services are added
	 * Sets the country of the user
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * Returns the first name of the user
	 * @return the firstName of the user
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * Sets the first name of the user
	 * @param firstName the firstName to set
	 * @throws NullPointerException if firstName is null
	 * @throws IllegalArgumentException if firstName is empty
	 */
	private void setFirstName(String firstName) {
		if(firstName == null) {
			throw new NullPointerException("First name cannot be null");
		}
		if("".equals(firstName)) {
			throw new IllegalArgumentException("First name cannot be empty.");
		}
		this.firstName = firstName;
	}


	/**
	 * Returns the last name of the user
	 * @return the lastName of the user
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * Sets the last name of the user
	 * @param lastName the lastName to set
	 * @throws NullPointerException if lastName is null
	 * @throws IllegalArgumentException if lastName is empty
	 */
	public void setLastName(String lastName) {
		if(lastName == null) {
			throw new NullPointerException("Last name cannot be null");
		}
		if("".equals(lastName)) {
			throw new IllegalArgumentException("Last name cannot be empty.");
		}
		this.lastName = lastName;
	}


	/**
	 * Returns the age of the user
	 * @return the age of the user
	 */
	public int getAge() {
		return age;
	}


	/**
	 * Sets the age of the user
	 * @param age the age to set
	 * @throws IllegalArgumentException if the age
	 * of the user is below 0 or over 118 subject to change
	 */
	public void setAge(int age) {
		if(age < 0 || age > 118 /** The Age of the oldest person can change */) {
			throw new IllegalArgumentException("Invalid Age.");
		}
		this.age = age;
	}
	
	/**
	 * To String method for users
	 */
	public String toString() {
		String s = firstName + ", " + lastName + ", " + gender + ", " + age + ", " +
				height + ", " + weight + ", " + country;
		return s;
	}
	
	/**
	 * Returns whether or not the use is edited
	 * @return edited whether or not the user is edited
	 */
	public boolean isEdited() {
		return edited;
	}
	
	/**
	 * Sets the field edited to the value in the parameter
	 * @param edited value to set field to
	 */
	public void setEdited(boolean edited) {
		this.edited = edited;
	}
}
