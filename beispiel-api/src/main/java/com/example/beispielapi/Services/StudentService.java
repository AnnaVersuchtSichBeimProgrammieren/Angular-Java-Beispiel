package com.example.beispielapi.Services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beispielapi.Models.Student;
import com.example.beispielapi.Repositories.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = this.studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email is already taken");
        }
        this.studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        boolean exists = this.studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Id does not exists");
        }
        this.studentRepository.deleteById(studentId);
    }

    @Transactional //Entity goes into managed state
    public void updateStudent(Long id, String name, String email) {
        Student existing = this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("The student does not exists"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(name, existing.getName())) {
            existing.setName(name);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(email, existing.getEmail())) {
            Optional<Student> emailTaken = studentRepository.findStudentByEmail(email);
            if (emailTaken.isPresent()) {
                throw new IllegalStateException("email is already taken");
            }
            existing.setEmail(email);
        }
    }
}
