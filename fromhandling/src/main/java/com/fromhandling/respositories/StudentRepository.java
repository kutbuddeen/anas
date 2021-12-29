package com.fromhandling.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fromhandling.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student getByStudentId(int studentId);
}
