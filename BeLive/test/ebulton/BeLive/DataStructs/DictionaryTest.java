package ebulton.BeLive.DataStructs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import ebulton.BeLive.BeLive;
import ebulton.BeLive.treatments.Database;
import ebulton.BeLive.treatments.Treatment;

class DictionaryTest {

	private Database db;
	private Dictionary d;
	private Treatment t;


	@BeforeEach
	void setUp() throws Exception {
		db = new Database("C:\\Users\\Elstan\\.git\\portfolio\\BeLive\\files\\treatments.txt");
		d = new Dictionary(db);
		t = new Treatment("country1", "honey", "mosquito bite", "Apply the honey directly to the mosquito bite", "found near bee hives look in trees", "itching", 4, 0);
	}

	@Test
	void testGetAll() {
		LinkedList<Treatment> treatments;
		
		//Head\Neck
		treatments = d.getAll(1);
		if(treatments.size() != 0){
			fail("Incorrect treatments found in dictionary");
		}

		//Chest
		treatments = d.getAll(2);
		if(treatments.size() != 0){
			fail("Incorrect treatments found in dictionary");
		}

		//Arm
		treatments = d.getAll(3);
		if(treatments.size() != 0){
			fail("Incorrect treatments found in dictionary");
		}	
		
		//Wrist/Hand
		treatments = d.getAll(4);
		if(treatments.size() != 1 /**&& !(treatments.get(0).equals(t))*/){
			fail("Incorrect treatments found in dictionary");
		}

		//Leg
		treatments = d.getAll(5);
		if(treatments.size() != 0){
			fail("Incorrect treatments found in dictionary");
		}

		//Foot/Ankle
		treatments = d.getAll(6);
		if(treatments.size() != 0){
			fail("Incorrect treatments found in dictionary");
		}

		return;
	}

	@Test
	void testGet() {
		String[] symptoms = {"itching"};
		String[] uses = {"mosquito bite"};
		LinkedList<Treatment> treatments = d.get(4, 0, symptoms, uses);
		if(treatments.size() == 1 && treatments.get(0).equals(t)){
			return;
		}
		else{
			fail("Incorrect treatments found in dictionary");
		}
	}

	@Test
	void testRemove() {
		String[] symptoms = {"itching"};
		String[] uses = {"mosquito bite"};
		LinkedList<Treatment> treatments = d.get(4, 0, symptoms, uses);
		if(treatments.size() == 1 && treatments.get(0).equals(t)){
			return;
		}
		else{
			fail("Incorrect treatments found in dictionary");
		}

		Treatment removed = d.remove(4, 0);
		if(t.equals(removed) && d.getAll(4).size() == 0){
			return;
		}
		else{
			fail("Failed to remove treatment");
		}
	}

	@Test
	void testAdd() {
		String[] symptoms = {"itching"};
		String[] uses = {"mosquito bite"};
		LinkedList<Treatment> treatments = d.get(4, 0, symptoms, uses);
		if(treatments.size() == 1 && treatments.get(0).equals(t)){
			return;
		}
		else{
			fail("Incorrect treatments found in dictionary");
		}

		//Create treatment to add
		Treatment t2 = new Treatment("Ugoslavia", "Boroline", "everything", "remedy from God's own country", "Kerala", "anything", 4, 0);
		String[] symptoms2 = {"anything"};
		String[] uses2 = {"everything"};
		d.add(4, t2);
		if(d.getAll(4).size() == 2 && t2.equals(d.get(4,0,symptoms2, uses2).get(0))){
			return;
		}
		else{
			fail("Failed to remove treatment");
		}
	}

}
