package com.rionass.testing.student;

import com.rionass.testing.student.exception.BadRequestException;
import com.rionass.testing.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student){
        Boolean existingEmail = studentRepository.selectExistsEmail(student.getEmail());

        if(existingEmail){
            throw new BadRequestException(
                    "Email : " + student.getEmail() + " Already taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        if(!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException(
                    "Student with ID : "+ studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

}
