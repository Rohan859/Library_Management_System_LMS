package com.student.lms.lms.Service;

import com.student.lms.lms.Entities.Student;
import com.student.lms.lms.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService
{
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student)
    {
        studentRepository.save(student);
        return "Added the student into db successfully";
    }
}
