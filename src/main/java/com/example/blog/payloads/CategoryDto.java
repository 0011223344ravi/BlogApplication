package com.example.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private Integer categoryId;
	@NotEmpty
	@Size(min =4 ,max =10, message ="categoryTitle must be min of 4 character and max of 10")
	private String categoryTitle;
	@NotNull
	private String categoryDescription;
}
