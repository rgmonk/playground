package org.example;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ExampleResponse {
	/**
     * Item ID.
     */
    @NotBlank
    private Integer id;

    /**
     * Item description.
     */
    @Size(max = 20)
    private String description;
    
    /**
     * This is a regex pattern
     */
    @Pattern(regexp="(TEST)|(LIVE)")
    private String patternString;

	public ExampleResponse(Integer id, String description, String patternString) {
		super();
		this.id = id;
		this.description = description;
		this.patternString = patternString;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPatternString() {
		return patternString;
	}

	public void setPatternString(String patternString) {
		this.patternString = patternString;
	}
    
    
}
