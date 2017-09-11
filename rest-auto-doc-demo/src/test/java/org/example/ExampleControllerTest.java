package org.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExampleControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	protected ObjectMapper objectMapper;

	protected MockMvc mockMvc;

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
				.alwaysDo(MockMvcRestDocumentation.document("{class-name}/{method-name}",
						Preprocessors.preprocessRequest(),
						Preprocessors.preprocessResponse(ResponseModifyingPreprocessors.replaceBinaryContent(),
								ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
								Preprocessors.prettyPrint())))
				.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation).uris().withScheme("http")
						.withHost("localhost").withPort(8080).and().snippets()
						.withDefaults(CliDocumentation.curlRequest(), HttpDocumentation.httpRequest(),
								HttpDocumentation.httpResponse(), AutoDocumentation.requestFields(),
								AutoDocumentation.responseFields(), AutoDocumentation.pathParameters(),
								AutoDocumentation.requestParameters(), AutoDocumentation.description(),
								AutoDocumentation.methodAndPath(), AutoDocumentation.section()))
				.build();
	}
	
	@Test
	public void getResponseTest() throws Exception {
	    mockMvc.perform(get("/"));	   
	}
}
