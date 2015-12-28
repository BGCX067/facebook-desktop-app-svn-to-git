package de.lebk.facebook.facebook.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import de.lebk.facebook.services.NewsfeedServiceImpl;
import de.lebk.facebook.user.UserCredentials;
import facebook4j.Post;
import facebook4j.ResponseList;

@RunWith(MockitoJUnitRunner.class)
public class NewsfeedServiceImplTest {

	private NewsfeedServiceImpl service;
	private String id;

	
	@Before
	public void setUp() throws Exception {
		service = new NewsfeedServiceImpl();
		service.connectUser(new UserCredentials("thomas-michael.koeper@gmx.de",""));
			
	}

	@Test
	public void testGetAllFeeds() throws Exception {
		ResponseList<Post> result = service.getAllFeeds();
		assertNotNull(result);
		id = result.get(0).getId();
	}
	
	@Test
	public void testGetFeed() throws Exception {
		
		 Post res = service.getFeed(Integer.parseInt(id));
		 assertNotNull(res);
	}
}
