package com.example.blog.payloads;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min =4 , message ="user must be min of 4 character")
	private String name ;
	@Email(message ="Email is not valid")
	private String email;
	@NotNull
	@Size@Size(min =4, max =10, message ="password should be between 4 and 10 character length")
	private String password;
	@NotNull
	private String about;

}
