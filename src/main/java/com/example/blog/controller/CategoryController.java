package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody  CategoryDto categoryDto){
		CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategoryDto , HttpStatus.CREATED);
		
	}
	//update 
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody  CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategoryDto , HttpStatus.CREATED);
		
		
	}
	
	//delete 
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer categoryId){
		  this.categoryService.deleteCategory( categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted Succsess", true ) , HttpStatus.OK);
		
		
	}
	
	//get 
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory( @PathVariable Integer categoryId){
		 CategoryDto getCategoryDto =  this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(getCategoryDto, HttpStatus.OK);
		
		
	}
	
	//getAll
	
	@GetMapping("/AllCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategories( ){
		  List<CategoryDto>  categoriesDto =this.categoryService.getCategories();
		  
		return   ResponseEntity.ok(categoriesDto);
		
		
	}

}
