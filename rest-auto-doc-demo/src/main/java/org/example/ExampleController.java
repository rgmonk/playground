package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExampleController {

	@GetMapping
	public ExampleResponse getResponse() {
		return new ExampleResponse(1, "Test", "TEST");
	}

}
