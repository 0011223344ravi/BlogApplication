package com.example.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.entities.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
@Autowired
private CategoryRepo categoryRepo ;
	
@Autowired
private ModelMapper modelMapper ;
	
	
	
 

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	
	 Category category = this.modelMapper.map(categoryDto, Category.class);
	 Category category1 = ((CrudRepository<Category,Integer>) this.categoryRepo).save(category);
	 return this.modelMapper.map(category1, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat = ((CrudRepository<Category,Integer>) this.categoryRepo).findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());		
		
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public void  deleteCategory(Integer categoryId) {
		
	Category cat  = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category" , "Category ID",categoryId));	
		this.categoryRepo.delete(cat);
	
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
	 
	Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category" ,"Category ID ", categoryId));
	
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		 List<Category> categories  = this.categoryRepo.findAll();
		List<CategoryDto> categoriesDto= categories .stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
	
	    return categoriesDto;
	}
}
