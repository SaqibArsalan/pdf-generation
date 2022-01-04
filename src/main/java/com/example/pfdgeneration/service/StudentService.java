package com.example.pfdgeneration.service;

import com.example.pfdgeneration.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class StudentService {

    public List<Student> getStudents() {
        final List<Student> students = List.of(
                new Student(1, "Saqib", "", LocalDate.now(), "PAKISTANI", "", true),
                new Student(2, "Ateeb", "", LocalDate.now(), "AMERICAN", "", true)
        );

        return students;
    }
}
