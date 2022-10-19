package com.rionass.testing.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path="api/vi/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudent();
    }

    @PostMapping
    public void addStudent(@Valid @RequestBody Student student){
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(
            @PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
}
