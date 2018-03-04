package com.example.demotest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> findAll(){
		return departmentRepository.findAll();
	}

	public Department findOne(Integer id) {
		return departmentRepository.findOne(id);
	}

	public Department store(Department department) {
		// TODO Auto-generated method stub
		return department;
	}
}
