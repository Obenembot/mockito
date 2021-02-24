package com.sam.repositories;

import com.sam.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends MongoRepository<Student,String> {
}
