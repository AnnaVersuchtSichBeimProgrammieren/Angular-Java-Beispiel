package com.example.beispielapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.beispielapi.Models.Student;
import com.example.beispielapi.Services.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @PostMapping("")
    public void registerNewStudent(@RequestBody Student student) {
        this.studentService.addNewStudent(student);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudentById(@PathVariable("studentId") Long id) {
        this.studentService.deleteStudentById(id);
    }

    @PutMapping("{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        this.studentService.updateStudent(id, name, email);
    }
}
