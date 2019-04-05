package projekti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void canGetProfile() throws Exception {
		mockMvc.perform(get("/users/profile")).andExpect(status().isOk()).andExpect(model().attribute("strings", contains("testi")));
	}

	@Test
	public void canGetPRofile2() throws Exception {
		MvcResult result = mockMvc.perform(get("/users/profile")).andExpect(status().isOk()).andReturn();
		Object obj = result.getModelAndView().getModel().get("strings");

		List<String> myList = (List<String>)obj;

		assertTrue("Should contain \"testi\"", myList.contains("testi"));
	}
}
