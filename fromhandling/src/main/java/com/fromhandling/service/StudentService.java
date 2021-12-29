package com.fromhandling.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fromhandling.entities.Student;
import com.fromhandling.respositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional(readOnly = true)
	public List<Student> getAllStudent(){
		List<Student> students = studentRepository.findAll();
		return students;
	}
	@Transactional(readOnly = true)
	public Student getByStudentId(int studentId) {
		return studentRepository.getByStudentId(studentId);
	}
	
	@Modifying
	@Transactional(readOnly = false)
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}
	@Modifying
	@Transactional(readOnly = false)
	public void deleteStudent(int studentId) {
		studentRepository.deleteById(studentId);
	}
	@Modifying
	@Transactional(readOnly = false)
	public void delete(Student student) {
		studentRepository.delete(student);
	}
}
