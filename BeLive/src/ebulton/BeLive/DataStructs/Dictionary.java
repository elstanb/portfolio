package ebulton.BeLive.DataStructs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import ebulton.BeLive.treatments.Database;
import ebulton.BeLive.treatments.Treatment;

/**
 * This class creates the Dictionary Data Structure that will hold treatments
 * based on the injury location
 * 
 * @author ElstanB
 *
 */
public class Dictionary {
	
	/** 
	 * Creates a Dictionary with key as the location of the treatment and
	 * value as the treatment
	 */
	public ArrayList<LinkedList<Treatment>> dictionary;
	
	/**
	 * Creates the dictionary with linked lists for each treatment
	 * at the integer location of the treatment in the dictionary
	 * The index in the array list specify the location of the treatment
	 * O(1) for get linked list containing all treatments at the specified location
	 * O(n) worst case get for individual treatments in the linked list
	 * Linked List was the data structure selected to hold the list of treatments at each section
	 * because the the lists of treatments are very large and most are to be displayed so  
	 * @param d the database for the dictionary
	 */
	public Dictionary(Database d) {
		dictionary = new ArrayList<LinkedList<Treatment>>(6);
		dictionary.add(0, new LinkedList<Treatment>());
		dictionary.add(1, new LinkedList<Treatment>());
		dictionary.add(2, new LinkedList<Treatment>());
		dictionary.add(3, new LinkedList<Treatment>());
		dictionary.add(4, new LinkedList<Treatment>());
		dictionary.add(5, new LinkedList<Treatment>());
		loadDatabase(d);
	}
	
	/**
	 * Adds treatments to the end of the linked list at the specified location
	 * O(1) for add method
	 * @param bodypart the body part that the treatment is applied to
	 * @param t the treatment to add to the dictionary
	 */
	public void add(int bodypart, Treatment t) {
		validate(t);
		dictionary.get(checkBodyPart(bodypart)).addLast(t);
	}
	
	/**
	 * Removes treatments from the linked list based on the specified location
	 * (THIS METHOD IS ONLY ADDED FOR THE RARE CHANCE WHERE YOU NEED TO REMOVE A TREATMENT)
	 * O(n) worst case for remove (METHOD NOT COMMONLY CALLED SO O(n) IS OK)
	 * @param bodypart the body part that the treatment is applied to
	 * @param lindex the location of the treatment to remove in the linked list
	 * @return the treatment that was removed
	 */
	public Treatment remove(int bodypart, int lindex) {
		Treatment r = dictionary.get(checkBodyPart(bodypart)).remove(lindex);
		return r;
	}
	
	/**
	 * Returns all treatments found at the specified location
	 * @param bodypart the body part that the treatment is applied to
	 * @return a linked list containing all of the treatments found in that area
	 */
	public LinkedList<Treatment> getAll(int bodypart) {
		return dictionary.get(checkBodyPart(bodypart));
	}
	
	/**
	 * Helper method that checks whether the full string contains any instance
	 * of the list of substrings
	 * @param fullString the string to check if it contains any substrings
	 * @param sub list of substrings to see if they are within the full string
	 * @return true if any sub string is found in the main string else false
	 */
	private boolean contains(String fullString, String[] sub) {
		for(int i = 0; i < sub.length; i++) {
			if(fullString.toLowerCase().contains(sub[i].toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Searches Linked List of treatments based on symptoms specified by the user O(mlogn)
	 * @param bodypart the body part that the treatment is applied to
	 * @param orientation the orientation of the treatment on the body
	 * @param symptom the symptoms of the treatment
	 * @param uses the uses of the treatment
	 * @return a linked list containing treatments that cures the specified orientation, symptoms, and uses
	 */
	public LinkedList<Treatment> get(int bodypart, int orientation, String[] symptoms, String[] uses) {
		LinkedList<Treatment> treatments = dictionary.get(checkBodyPart(bodypart));
		LinkedList<Treatment> newTreatments = new LinkedList<Treatment>();
		Iterator<Treatment> it = treatments.iterator();
		LinkedList<Treatment> ret = new LinkedList<Treatment>();
		int counter = 0;
		while(it.hasNext()) {																					//O(n)
			Treatment t = it.next();																		//O(1)
			newTreatments.addLast(t);
			if(t.getOrientation() == orientation) {														//O(1)
				if(contains(t.getSymptoms(), symptoms)) {										//O(1)
					if(contains(t.getUses(), uses)) {										//O(1)
						System.out.println("Found Treatment");
						ret.add(t);																//O(1)
						//Move To Front Heuristic when found to increase search efficiency
						//Allows for most searched treatments to move to the front making get 
						//NOTE: Can result in starvation of least searched treatments
						newTreatments.remove(counter);												//O(m)
						newTreatments.addFirst(t);												    //O(1)
					}
				}
			}
			counter++;																			//O(1)
		}
		if(ret.size() == 0) {
			return treatments;
		}
		else {
			dictionary.set(checkBodyPart(bodypart), newTreatments);
		}
		return ret;
	}
	
	/**
	 * Ensures that the treatment is a treatment
	 * @param t the treatment to validate
	 */
	private void validate(Treatment t) {
		if(!(t instanceof Treatment)) {
			throw new IllegalArgumentException("Invalid Treatment.");
		}
	}
	
	/**
	 * Checks the body part of the treatment
	 * @param bodypart the body part to check
	 * @return the index of the body part in the dictionary
	 */
	private int checkBodyPart(int bodypart) {
		int index = bodypart - 1;
		if(index < 0 || index > 5) {
			throw new IllegalArgumentException("Invalid Index.");
		}
		return index;
	}
		
	/**
	 * Loads the database into the dictionary
	 * @param d the database to load
	 */
	private void loadDatabase(Database d) {
		ArrayList<Treatment> treatments = d.getTreatments();
		for(int i = 0; i < treatments.size(); i++) {
			add(treatments.get(i).getBodyPart(), treatments.get(i));
		}
	}
	
}
