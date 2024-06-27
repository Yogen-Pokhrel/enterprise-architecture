package com.ea.lab4part1.service;

import com.ea.lab4part1.domain.Department;
import com.ea.lab4part1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findById(Long id){
        return departmentRepository.findById(id).get();
    }

    public void save(Department department){
        departmentRepository.save(department);
    }
}
