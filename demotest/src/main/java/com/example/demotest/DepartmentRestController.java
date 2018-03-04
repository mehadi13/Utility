package com.example.demotest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/departments")
public class DepartmentRestController {
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping
	public List<Department> findAll(){
		return departmentService.findAll();
		/*if (departments != null) {
		    return new ApiResponse(ClassWrapper.getWrapper("departments", departments))
		    		.send(HttpStatus.OK,200,"Successful");
	        } else {
            return new ApiResponse().send(HttpStatus.NOT_FOUND, "Department List not found!");
        }*/
	}
	
	@GetMapping("/{id}")
	public Department  getOne(@PathVariable Integer id){
		return departmentService.findOne(id);
	/*	if (department != null) {
            return new ApiResponse(ClassWrapper.getWrapper("department", department))
            		.send(HttpStatus.OK,200,"Successful");
        } else {
            return new ApiResponse().send(HttpStatus.NOT_FOUND, "Department not found!");
        }*/
	}
	
	@PostMapping
	public Department store(@RequestBody Department department){
		return departmentService.store(department);
	}
}
