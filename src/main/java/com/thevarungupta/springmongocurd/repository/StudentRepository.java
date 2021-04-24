package com.thevarungupta.springmongocurd.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thevarungupta.springmongocurd.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{

}
