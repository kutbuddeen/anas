package com.fromhandling.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fromhandling.entities.Student;
import com.fromhandling.respositories.StudentRepository;
import com.fromhandling.service.StudentService;

@Controller
@RequestMapping("/")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("show-student.htm")
	public String showStudent(Model model) {
		List<Student> students = studentService.getAllStudent();
		model.addAttribute("student", students);
		return "show-student";
	}

	/*
	 * @GetMapping("/find/{studentId}") public String getStudent(Model
	 * model,@PathVariable("studentId") int studentId) { Optional<Student> student =
	 * null; student = studentService.getStuentById(studentId);
	 * model.addAttribute("students",student); return "student-info"; }
	 */
	@GetMapping("student-form.htm")
	public String userForm(Model model) {
		Student student = null;
		student = new Student();
		model.addAttribute("student", student);
		return "student-form";
	}

	/*
	 * @RequestMapping(value = "/save.htm", method = RequestMethod.POST) public
	 * String saveStudent(@ModelAttribute("student") Student student, BindingResult
	 * bindingResult) { studentService.saveStudent(student);
	 * 
	 * return "redirect:show-student.htm"; }
	 */
	@PostMapping("save.htm")
	public String saveStudent(@ModelAttribute("student") Student student, BindingResult bindingResult) {
		studentService.saveStudent(student);
		return "redirect:home.htm";
	}

	@GetMapping("home.htm")
	public String showHome() {
		return "home";
	}

	@GetMapping("{studentId}/delete.htm")
	public String delete(@PathVariable("studentId") int studentId, Model model) {

		Student student = studentService.getByStudentId(studentId);
		List<Student> students = studentService.getAllStudent();
		System.out.println("Student name : " + student.getStudentName());
		studentService.delete(student);
		model.addAttribute("student", students);

		return "redirect:/show-student.htm";
	}

	@GetMapping("{studentId}/edit.htm")
	public String updateForm(@PathVariable("studentId") int studentId, Model model) {
		Student student = studentService.getByStudentId(studentId);
		model.addAttribute("student", student);
		return "/update-form";
	}
	
	@PostMapping("{studentId}/update.htm")
	public String updateStudent(@PathVariable("studentId") long studentId, Student student, BindingResult result,
			Model model) {

		studentService.saveStudent(student);
		model.addAttribute("student", studentService.getAllStudent());
		return "redirect:/show-student.htm";
	}
}
