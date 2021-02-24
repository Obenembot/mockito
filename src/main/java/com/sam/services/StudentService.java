package com.sam.services;

import com.sam.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    
    Student save(Student student);
    
    Student update(Student student);
    
    void delete(String id);
    
    void deleteAll();
    
    List<Student> getAll();
    
    Student findById(String id);
    
    Page<Student> search(Pageable pageable);
}
