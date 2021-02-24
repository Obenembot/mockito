package com.sam.services.iml;

import com.sam.models.Student;
import com.sam.repositories.StudentRepository;
import com.sam.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceIml implements StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
    
    @Override
    public Student update(Student student) {
        
        return studentRepository.save(student);
    }
    
    @Override
    public void delete(String id) {
        studentRepository.deleteById(id);
    }
    
    @Override
    public void deleteAll() {
        this.studentRepository.deleteAll();
        
    }
    
    @Override
    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }
    
    @Override
    public Student findById(String id) {
        return this.studentRepository.findById(id).orElse(null);
    }
    
    @Override
    public Page<Student> search(Pageable pageable) {
        return this.studentRepository.findAll(pageable);
    }
}
