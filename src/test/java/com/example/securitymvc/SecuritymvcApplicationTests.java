package com.example.securitymvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SpringSecurityController.class)
public class SecuritymvcApplicationTests {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private MockMvc mvc;

	@WithMockUser(value = "user")
	@Test
	public void testUserOkStatus() throws Exception {
		mvc.perform(get("/user/user").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@WithMockUser(roles = "NAJNOWSZY")
	@Test
	public void testNajnowszyOkStatus() throws Exception {
		mvc.perform(get("/najnowszy/user"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUserForbiddenStatus() throws Exception {
		mvc.perform(get("/user/user").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testPermitAllOkStatus() throws Exception {
		mvc.perform(get("/healthcheck").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@WithMockUser(roles = "ADMIN")
	@Test
	public void testDeleteOkStatus() throws Exception {
		mvc.perform(post("/delete/user").with(SecurityMockMvcRequestPostProcessors.csrf()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@WithMockUser(roles = "NOWY")
	@Test
	public void testUpdateOkStatus() throws Exception {
		mvc.perform(get("/update/user").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
