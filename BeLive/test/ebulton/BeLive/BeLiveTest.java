package ebulton.BeLive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ebulton.BeLive.BeLive;
import ebulton.BeLive.user.User;

class BeLiveTest {
	
	BeLive newTest;
	
	BeLive returningTest;
	
	User user;

	@BeforeEach
	void setUp() throws Exception {
		user = new User("First", "Last", true, 34, 49, 225, "location1");
		newTest = new BeLive(user, 4, 0, "description", "symptom");
		returningTest = new BeLive("John", "Doe");
	}

	@Test
	void testFindTreatments() {
		
	}
	
	@Test
	void testFindUser() {
		
	}
	

}
