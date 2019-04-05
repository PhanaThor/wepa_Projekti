package projekti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTests {

	private final String API_URI = "/users";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void canGetUsers() throws Exception {
		MvcResult result = mockMvc.perform(get(API_URI)).andExpect(status().isOk()).andReturn();

		assertTrue("Model should contain \"users\"", result.getModelAndView().getModel().containsKey("users"));
	}
}
