package com.sam.web;

import com.sam.models.Student;
import com.sam.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {
    
    private StudentService studentService;
    
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @PostMapping("/students")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.save(student));
    }
    
    @PutMapping("/students")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.update(student));
    }
    
    @GetMapping("/students")
    public List<Student> getAll() {
        return studentService.getAll();
    }
    
    @GetMapping("/students/{id}")
    public Student getById(@PathVariable String id) {
        return studentService.findById(id);
    }
    
    @DeleteMapping("/students/{id}")
    public void deleteById(@PathVariable String id) {
        this.studentService.delete(id);
    }
    
    @GetMapping("/_searchstudents")
    public Page<Student> get(Pageable pageable){
        return  this.studentService.search(pageable);
    }
}
