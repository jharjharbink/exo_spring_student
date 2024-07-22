package org.example.etudiant.service;

import org.example.etudiant.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StudentService {
    private final Map<Long, Student> students;

    public StudentService() {
        this.students = new HashMap<>();

        Student student1 = new Student(0, "Jean", "Lacoste", 18, "jeanlacoste@gmail.com");
        Student student2 = new Student(1, "Jeanne", "Lacoste", 20, "jeannelacoste@gmail.com");
        Student student3 = new Student(2, "Big", "Babbou", 60, "lologarnier@gmail.com");
        Student student4 = new Student(3, "Sacha", "Debourgpalette", 60, "sacha@gmail.com");
        Student student5 = new Student(4, "Loic", "Vanne", 27, "flamish@gmail.com");
        Student student6 = new Student(5, "Jean", "Valjean", 18, "cotedemaille@gmail.com");

        students.put(student1.getId(),student1);
        students.put(student2.getId(),student2);
        students.put(student3.getId(),student3);
        students.put(student4.getId(),student4);
        students.put(student5.getId(),student5);
        students.put(student6.getId(),student6);
    }

    public Student getStudent(long id) {
        return students.get(id);
    }

    public List<Student> getAllStudents() {
        return students.values().stream().toList();
    }

    public void addStudent(Student student) {
        long studentId = students.size();

        while (students.containsKey(studentId))
            studentId = students.size();

        student.setId(studentId);
        students.put(student.getId(), student);
    }

    public void updateStudent(Student student) {
        deleteStudent(student.getId());
        students.put(student.getId(), student);
    }

    public void deleteStudent(long id) {
        students.remove(id);
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        List<Student> foundStudents = new ArrayList();
        for (Student student : students.values())
            if (Objects.equals(student.getFirstName(), firstName))
                foundStudents.add(student);
        return foundStudents;
    }
}
