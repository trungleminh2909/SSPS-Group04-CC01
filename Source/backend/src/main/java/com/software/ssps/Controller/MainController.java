package com.software.ssps.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.software.ssps.Entity.Student;
import com.software.ssps.Entity.paymentHistory;
import com.software.ssps.Entity.printHistory;

@Controller
public class MainController {
    //////////////////// .......Hard coded data part.......////////////////////
    // Add hardcoded data list/ saving place here
    private List<Student> studentList;
    private List<paymentHistory> paymentHistory;
    private List<printHistory> printHistory;

    // Write initializer in the Main Controller constructor
    public MainController() {
        studentList = new ArrayList<>();
        Student student1 = new Student(
                "Nguyen Van A", // studentName
                "2252123", // studentID
                "hcmutUser1", // username
                "hcmutPass1", // password
                "student1@hcmut.edu.vn", // studentEmail
                "Ho Chi Minh", // studentAddress
                "avatar1.png", // avatar
                100, // pageNumber
                new ArrayList<>() // uploadedFiles (empty list for now)
        );
        Student student2 = new Student(
                "Nguyen Van B", // studentName
                "2252456", // studentID
                "hcmutUser2", // username
                "hcmutPass2", // password
                "student2@hcmut.edu.vn", // studentEmail
                "Ho Chi Minh", // studentAddress
                "avatar2.png", // avatar
                100, // pageNumber
                new ArrayList<>() // uploadedFiles (empty list for now)
        );
        studentList.add(student1);
        studentList.add(student2);

        paymentHistory = new ArrayList<>();
        printHistory = new ArrayList<>();
    }

    //////////////////// .......Hard coded data part.......////////////////////
    @GetMapping("/ssps/homepage")
    public String mainpage() {
        return "homepage";
    }

    @GetMapping("/ssps/login")
    public String login() {
        return "login";
    }

    @PostMapping("/ssps/login")
    public String validateLogin(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String loginOption,
            Model model) {
        if ("SPSO".equals(loginOption)) {
            if ("spsoUser".equals(username) && "spsoPass".equals(password)) {
                // To do: SPSO homepage here
                return "";
            }
        } else if ("HCMUT".equals(loginOption)) {
            for (Student student : studentList) {
                if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                    // If student is found, add their details to the model
                    model.addAttribute("studentName", student.getStudentName());
                    model.addAttribute("studentID", student.getStudentID());
                    model.addAttribute("email", student.getStudentEmail());
                    model.addAttribute("address", student.getStudentAddress());
                    model.addAttribute("pageNumber", student.getPageNumber());
                    // Redirect to student home page with the student ID
                    return "redirect:/ssps/" + student.getStudentID() + "/home";
                }
            }
        }
        // Invalid login handling
        model.addAttribute("error", "Invalid credentials or login option.");
        return "homepage";
    }

    @GetMapping("/ssps/logout")
    public String logout(Model model) {
        return "redirect:/ssps/homepage";
    }

    @GetMapping("/ssps/{studentID}/home")
    public String home(@PathVariable String studentID, Model Model) {

        return "studentHome";
    }

    @GetMapping("/ssps/{studentID}/info")
    public String studentInfo(@RequestParam String param) {
        return "studentInfo";
    }

    @GetMapping("/ssps/{studentID}/print")
    public String print() {
        return "studentPrint";
    }

    @GetMapping("ssps/{studentID}/history")
    public String history(@RequestParam String param) {
        return "studentHistory";
    }

    @GetMapping("ssps/{studentID}/payment")
    public String payment(@RequestParam String param) {
        return "studentPayment";
    }

}
