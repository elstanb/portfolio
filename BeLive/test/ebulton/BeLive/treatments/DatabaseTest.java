package ebulton.BeLive.treatments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ebulton.BeLive.user.User;

class DatabaseTest {

	Database db;
	User u;
	User u2;
	Treatment t;

	@BeforeEach
	void setUp() throws Exception {
		db = new Database("C:\\Users\\Elstan\\.git\\portfolio\\BeLive\\files\\treatments.txt");
		u = new User("Jeff", "Schmidt", false, 24, 70, 170, "United States");
		u2 = new User("Jeff", "Schmidt", false, 24, 75, 190, "United States");
		t = new Treatment("country1", "honey", "mosquito bite", "Apply the honey directly to the mosquito bite", "found near bee hives look in trees", "itching", 4, 0);
	}

	@Test
	void testStoreUser() {
		int size_before = db.getUsers().size();
		db.storeUser(u);
		if (size_before + 1 == db.getUsers().size()){
			if(db.getUsers().getLast().equals(u)){
				return;
			}
		}
		fail("User not stored");
	}

	@Test
	void testEditUser() {
		db.editUser(u2);
		if(db.getUsers().getLast().equals(u2)){
			return;
		}
		else{
			fail("User not edited");
		}
	}

	@Test
	void testGetTreatments() {
		ArrayList<Treatment> list= new ArrayList<Treatment>();
		list.add(t);
		if(list.get(0).equals(db.getTreatments().get(0)) && list.size() == db.getTreatments().size()){
			return;
		}
		fail("Unable to get treatments");
	}

	@Test
	void testGetUsers() {
		ArrayList<User> list= new ArrayList<User>();
		list.add(u2);
		if(list.size()+7 == db.getUsers().size()){
			return;
		}
		fail("Unable to get users");
	}

	@Test
	void testGetCounterPart1() {
		if(db.getCounterPart1() == 0){
			return;
		}
		fail("Unable to get Counter for Part 1");
	}

	@Test
	void testGetCounterPart2() {
		if(db.getCounterPart2() == 0){
			return;
		}
		fail("Unable to get Counter for Part 2");
	}

	@Test
	void testGetCounterPart3() {
		if(db.getCounterPart3() == 0){
			return;
		}
		fail("Unable to get Counter for Part 3");
	}

	@Test
	void testGetCounterPart4() {
		if(db.getCounterPart4() == 1){
			return;
		}
		fail("Unable to get Counter for Part 4");
	}

	@Test
	void testGetCounterPart5() {
		if(db.getCounterPart5() == 0){
			return;
		}
		fail("Unable to get Counter for Part 5");
	}

	@Test
	void testGetCounterPart6() {
		if(db.getCounterPart6() == 0){
			return;
		}
		fail("Unable to get Counter for Part 6");
	}

}
