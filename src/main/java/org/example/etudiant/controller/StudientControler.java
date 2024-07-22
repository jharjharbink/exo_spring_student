package org.example.etudiant.controller;

import org.example.etudiant.model.Student;
import org.example.etudiant.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class StudientControler {

    private final StudentService studentService;

    public StudientControler(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("student", new Student());
        return "index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "list";
    }

    @GetMapping("/inscription")
    public String inscription(Model model){
        model.addAttribute("student",new Student());
        return "inscription";
    }

    @PostMapping("/inscription/add")
    public String submitContact(@ModelAttribute("student") Student student){
        studentService.addStudent(student);
        return "redirect:/list";
    }


    @GetMapping("/detail")
    public String detail(@RequestParam(value = "firstName",required = false) String firstName, Model model) {
        List<Student> students = studentService.getStudentsByFirstName(firstName);
        if(students.size() > 1){
            model.addAttribute("students", students);
            return "list";
        } else {
            model.addAttribute("student", students.get(0));
            model.addAttribute("update", false);
            return "detail";
        }
    }


    @GetMapping("detail/id")
    public String detailId(@RequestParam(value = "studentId") long studentId, Model model) {
        Student student = studentService.getStudent(studentId);
        model.addAttribute("student", student);
        model.addAttribute("update", false);

        System.out.println("/search");
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "studentId") long studentId, Model model) {
        studentService.deleteStudent(studentId);
        List<Student> students = studentService.getAllStudents();
        model.addAttribute( "students", students);
        return "list";
    }

    @GetMapping("/update")
    public String getUpdatePage(@RequestParam(value = "studentId") long studentId, Model model) {
        Student student = studentService.getStudent(studentId);
        model.addAttribute("student", student);
        model.addAttribute("update", true);
        return "detail";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute("student") Student student, Model model) {
        studentService.updateStudent(student);

        List<Student> students = studentService.getAllStudents();
        model.addAttribute( "students", students);
        return "list";
    }


}
