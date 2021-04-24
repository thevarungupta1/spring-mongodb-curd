package com.thevarungupta.springmongocurd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.thevarungupta.springmongocurd.model.Student;
import com.thevarungupta.springmongocurd.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public ResponseEntity<?> getAllStudents(){
		List<Student> students = studentRepository.findAll();
		if(students.size() > 0) {
			return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("no students found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/students")
	public ResponseEntity<?> createStudent(@RequestBody Student student){
		try {
			this.studentRepository.save(student);
			return new ResponseEntity<Student>(student, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") String id){
		Optional<Student> studeOptional = studentRepository.findById(id);
		if(studeOptional.isPresent()) {
			return new ResponseEntity<>(studeOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("student not found with id "+ id, HttpStatus.NOT_FOUND);
		}
	}	
}
