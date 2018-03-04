package com.example.demotest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DepartmentRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;


	@Test
	public void findAll() throws Exception {
		// given
		List<Department> departments = new ArrayList<Department>();
		Department department = new Department();
		department.setId(1);
		department.setName("dept1");
		departments.add(department);

		// when
		Mockito.when(departmentService.findAll()).thenReturn(departments);

		// then
		mockMvc.perform(
				MockMvcRequestBuilders.get("/departments").accept(
						MediaType.APPLICATION_JSON))
				.andExpect(
						MockMvcResultMatchers.content().contentType(
								MediaType.APPLICATION_JSON))
				.andExpect(
						MockMvcResultMatchers.jsonPath("[1]").value(department));

		//Mockito.verify(departmentService).findAll();
	}
	
	@Test
	public void store() throws Exception {
		// given
		Department department = new Department();
		department.setId(2);
		department.setName("dept2");

		// when
		Mockito.when(departmentService.store(department)).thenReturn(department);

		// then
		/*mockMvc.perform(
				MockMvcRequestBuilders.post("/departments")
				.content(toJson(department))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content()
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("[0]")
						.value(department));*/
		String departmentJSON = "{\"id\":2,\"name\":\"dept2\"}";
		System.out.println(departmentJSON);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/departments")
				.accept(MediaType.APPLICATION_JSON)
				.content(departmentJSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse());
		
		MockHttpServletResponse response = result.getResponse();
		

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		//Mockito.verify(departmentService).findAll();
	}
	
	private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
}
	private Object toObj(byte[] data) throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
}
}
