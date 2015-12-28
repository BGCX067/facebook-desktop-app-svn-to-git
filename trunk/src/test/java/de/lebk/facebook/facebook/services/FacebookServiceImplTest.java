package de.lebk.facebook.facebook.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.lebk.facebook.services.FacebookServiceImpl;
import de.lebk.facebook.user.UserCredentials;
import facebook4j.Facebook;

public class FacebookServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUserLogin() throws Exception {
		FacebookServiceImpl impl = new FacebookServiceImpl();
		 Facebook res = impl.userLogin(new UserCredentials("thomas-michael.koeper@gmx.de", "!?tH22011989***"));
		 Assert.assertNotNull(res);
	}

}
