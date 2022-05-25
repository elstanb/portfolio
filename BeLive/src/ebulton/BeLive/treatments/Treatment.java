package ebulton.BeLive.treatments;
/**
 * This class constructs the Treatment Object for the BeLive application
 * @author ElstanB
 */
public class Treatment {
    
    /** The country of where the treatment can be found */
    private String country;
    
    /** The name of the treatment */
    private String name;
    
    /** The uses of the treatment */
    private String uses;
    
    /** The description of the treatment */
    private String description;
    
    /** The location in country to find the treatment */
    private String location;
    
    /** The location in the body where the treatment is 1(Head\Neck) 2(Chest) 3(Arm) 4(Wrist/Hand) 5(Leg) 6(Foot/Ankle) */
    private int bodyPart;
    
    /** Whether or not the injury is on the front or back of the bodyPart */
    private int orientation;
    
    /** The symptoms of the treatment */
    private String symptoms;    
    
    public Treatment(String country, String name, String uses, String description, String location, String symptoms, int bodyPart, int orientation){
        //Initialize private fields
    	this.setCountry(country);
    	this.setName(name);
    	this.setUses(uses);
    	this.setDescription(description);
    	this.setLocation(location);
        this.setBodyPart(bodyPart, orientation);
        this.setOrientation(orientation);
        this.setSymptoms(symptoms);
    }

	/**
	 * Returns the country of origin for the treatment
	 * @return the country of origin for the treatment
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country of origin for the treatment
	 * @param country the country of origin for the treatment to set
	 * @throws NullPointerException if country is null
	 * @throws IllegalArgumentException if the country is an empty String
	 * POTENTIAL TO DO: add database to ensure valid country is entered
	 */
	private void setCountry(String country) {
		if(country == null) {
			throw new NullPointerException("Country of origin cannot be null.");
		}
		if("".equals(country)) {
			throw new IllegalArgumentException("Country of origin cannot be an empty String.");
		}
		this.country = country;
	}

	/**
	 * Returns the name of the treatment
	 * @return the name of the treatment
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the treatment
	 * @param name the name to set
	 * @throws NullPointerException if the name is null
	 * @throws IllegalArgumentException if the name is an empty String
	 */
	private void setName(String name) {
		if(name == null) {
			throw new NullPointerException("Name cannot be null.");
		}
		if("".equals(name)) {
			throw new IllegalArgumentException("Name cannnot be an empty String.");
		}
		this.name = name;
	}

	/**
	 * Returns the description of the treatment
	 * @return the description of the treatment
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the treatment
	 * @param description the description of the treatment to set
	 * @throws NullPointerException if the description of the treatment is null
	 * @throws IllegalArgumentException if the description of the treatment is an empty String
	 */
	private void setDescription(String description) {
		if(description == null) {
			throw new NullPointerException("The description of the treatment cannot be null.");
		}
		if("".equals(description)) {
			throw new IllegalArgumentException("The description of the treatement cannot be an empty String.");
		}
		this.description = description;
	}

	/**
	 * Returns where the treatment is found in the country
	 * @return the location of the treatment in the country
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the treatment within the country
	 * @param location the location of the treatment to set
	 * @throws NullPointerException if the location is null
	 * @throws IllegalArgumentException if the location is an empty String
	 */
	private void setLocation(String location) {
		if(location == null) {
			throw new NullPointerException("The location of the treatment cannot be null.");
		}
		if("".equals(location)) {
			throw new IllegalArgumentException("The location of the treatment cannot be an empty String.");
		}
		this.location = location;
	}

	/**
	 * Returns the body part the treatment treats
	 * @return the bodyPart
	 */
	public int getBodyPart() {
		return bodyPart;
	}
	
	/**
	 * Returns the orientation of the body part
	 * @return the orientation of the body part
	 */
	public int getOrientation() {
		return orientation;
	}
	
	/**
	 * Sets the orientation of the body part
	 * @return the orientation of the body part
	 */
	private void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	/**
	 * Sets the body part the treatment treats
	 * @param bodyPart the bodyPart to set
	 * @param orientation the orientation of the body part
	 * @throws IllegalArgumentException if invalid
	 * integer for the body part is entered
	 */
	private void setBodyPart(int bodyPart, int orientation) {
		if(bodyPart < 1 || bodyPart > 6) {
			throw new IllegalArgumentException("Invalid Body Part.");
		}
		if(orientation < 0 || orientation > 1) {
			throw new IllegalArgumentException("Invalid Orientaion.");
		}
		this.bodyPart = bodyPart;
		this.orientation = orientation;
	}

	/**
	 * Returns the uses of the treatement
	 * @return the uses of the treatment
	 */
	public String getUses() {
		return uses;
	}

	/**
	 * Sets the uses of the treatment
	 * @param uses the uses of the treatment to set
	 * @throws NullPointerException if the uses are null
	 * @throws IllegalArgumentException if the uses are an empty String
	 */
	private void setUses(String uses) {
		if(uses == null) {
			throw new NullPointerException("The location of the treatment cannot be null.");
		}
		if("".equals(uses)) {
			throw new IllegalArgumentException("The location of the treatment cannot be an empty String.");
		}
		this.uses = uses;
	}

	/**
	 * Returns the symptoms of the treatment
	 * @return the symptoms of the treatment
	 */
	public String getSymptoms() {
		return symptoms;
	}

	/**
	 * Sets the symptoms of the treatment
	 * @param symptoms the symptoms of the treatment to set
	 */
	 private void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
    
}
    