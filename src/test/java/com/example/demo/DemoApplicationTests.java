package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class DemoApplicationTests {

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext context;

	@Before
	public void setupMockMvc() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void contextLoads() {

		try {
			String responseString = mvc.perform(get("/greeting")    //请求的url,请求的方法是get
					.contentType(MediaType.APPLICATION_JSON)  //数据的格式
					.param("name","123456789")         //添加参数
			).andExpect(status().isOk())    //返回的状态是200
					.andDo(print())         //打印出请求和相应的内容
					.andReturn().getResponse().getContentAsString();
			System.out.println("--------返回的json = " + responseString);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
