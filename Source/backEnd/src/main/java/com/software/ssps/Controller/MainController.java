package com.software.ssps.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.software.ssps.Entity.Admin;
import com.software.ssps.Entity.Student;
import com.software.ssps.Entity.adminHistory;
import com.software.ssps.Entity.paymentHistory;
import com.software.ssps.Entity.printHistory;
import java.util.Optional;

@Controller
public class MainController {
    //////////////////// .......Hard coded data part.......////////////////////
    // Add hardcoded data list/ saving place here
    private List<Student> studentList;
    private List<paymentHistory> paymentHistory;
    private List<printHistory> printHistory;
    private List<Admin> adminList;
    private List<adminHistory> adminHistories;

    // Write initializer in the Main Controller constructor
    public MainController() {
        studentList = new ArrayList<>();
        adminList = new ArrayList<>();

        Admin admin = new Admin("Le Hoang",
                "123456",
                "admin", "admin",
                "admin@hcmut.edu.vn",
                "hcm",
                "hoang.png",
                100,
                new ArrayList<>());
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
        adminList.add(admin);

        paymentHistory = new ArrayList<>();
        printHistory = new ArrayList<>();
        adminHistories = new ArrayList<>();
    }

    //////////////////// .......Hard coded data part.......////////////////////

    // #region student
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

    // #endregion

    // #region Admin

    ///////////////////////////// Account List/////////////////////////////
    @GetMapping("/ssps/admin/accountList")
    public String accountList() {
        return "accountList";
    }

    @GetMapping("/ssps/admin/accountList/adminAccountList")
    @ResponseBody
    public List<Admin> adminAccountList() {
        return adminList;
    }

    // untab at the version finall
    // @GetMapping("/ssps/admin/accountList/spsoAccountList")
    // @ResponseBody
    // public List<SPSO> spsoAccountList() {
    // return spsoList;
    // }

    @GetMapping("/ssps/admin/accountList/studentAccountList")
    @ResponseBody
    public List<Student> studentAccountList() {
        return studentList;
    }

    @GetMapping("/ssps/admin/adminLog")
    public String adminLog(Model model) {
        StringBuilder log = new StringBuilder("Log: \n");
        for (adminHistory history : adminHistories) {
            log.append(history.toString()).append('\n');
        }
        model.addAttribute("log", log.toString());
        return "adminLog";
    }

    // #region add account

    @GetMapping("ssps/admin/addAccount")
    public String addAccount() {
        // This should have verification
        return "addAccount";
    }

    @PostMapping("/ssps/admin/addAdmin")
    @ResponseBody
    public String addAdmin(@RequestParam String adminName,
            @RequestParam String adminID,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String adminEmail,
            @RequestParam String adminAddress,
            @RequestParam String avatar,
            @RequestParam Integer pageNumber) {
        Admin newAdmin = new Admin(adminName, adminID, username, password, adminEmail, adminAddress, avatar, pageNumber,
                new ArrayList<>());
        adminList.add(newAdmin);
        adminHistory history = new adminHistory("this admin", "Create Admin Account", newAdmin.toString());
        adminHistories.add(history);
        return "redirect:/ssps/admin/accountList";
    }

    // @PostMapping("/ssps/admin/addSPSO")
    // public String addSPSO(@RequestParam String spsoName,
    // @RequestParam String adminID,
    // @RequestParam String username,
    // @RequestParam String password,
    // @RequestParam String spsoEmail,
    // @RequestParam String spsoAddress,
    // @RequestParam String avatar,
    // @RequestParam Integer pageNumber) {
    // SPSO newSPSO = new SPSO(
    // spsoName,
    // spsoID, username, password,
    // spsoEmail,
    // spsoAddress, avatar, pageNumber,
    // new ArrayList<>());
    // spsoList.add(newSPSO);
    // adminHistory history = new adminHistory("this admin", "Create SPSO Account",
    // newSPSO.toString());
    // adminHistories.add(history);
    // return "redirect:/ssps/admin/accountList";
    // }

    @PostMapping("/ssps/admin/addStudent")
    @ResponseBody
    public String addStudent(@RequestParam String studentName,
            @RequestParam String studentID,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String studentEmail,
            @RequestParam String studentAddress,
            @RequestParam String avatar,
            @RequestParam Integer pageNumber) {
        Student newStudent = new Student(studentName, studentID, username, password, studentEmail, studentAddress,
                avatar, pageNumber, new ArrayList<>());
        studentList.add(newStudent);
        adminHistory history = new adminHistory("this admin", "Create Student Account", newStudent.toString());
        adminHistories.add(history);
        return "redirect:/ssps/admin/accountList";
    }

    // #endregion

    // #region check account info

    @GetMapping("/ssps/admin/adminInfo/{adminID}")
    public String adminInfo(@PathVariable String adminID, Model model) {
        Optional<Admin> admin = adminList.stream().filter(a -> a.getAdminID().equals(adminID)).findFirst();
        if (admin.isPresent()) {
            model.addAttribute("admin", admin.get());
            return "adminInfo";
        } else {
            return "redirect:/ssps/admin/accountList";
        }
    }

    // @GetMapping("/ssps/admin/spsoInfo/{spsoID}")
    // public String spsoDetails(@PathVariable String spsoID, Model model) {
    // Optional<SPSO> spso = spsoList.stream().filter(s ->
    // s.getSPSOID().equals(studentID)).findFirst();
    // if (spso.isPresent()) {
    // model.addAttribute("spso", spso.get());
    // return "spsoInfo";
    // } else {
    // return "redirect:/ssps/admin/accountList";
    // }
    // }

    // @GetMapping("/ssps/admin/studentInfo/{studentID}")
    // public String studentInfo(@PathVariable String studentID, Model model) {
    // Optional<Student> student = studentList.stream().filter(s ->
    // s.getStudentID().equals(studentID)).findFirst();
    // if (student.isPresent()) {
    // model.addAttribute("student", student.get());
    // return "studentInfo";
    // } else {
    // return "redirect:/ssps/admin/accountList";
    // }
    // }

    // #endregion

    // #region update account
    /////////////////////////////// Note: This only works by using a form like
    // adding account

    @PutMapping("/ssps/admin/updateAdmin/{adminID}")
    @ResponseBody
    public String updateAdmin(@PathVariable String adminID,
            @RequestParam String adminName,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String adminEmail,
            @RequestParam String adminAddress,
            @RequestParam String avatar,
            @RequestParam Integer pageNumber) {
        adminList.stream().filter(a -> a.getAdminID().equals(adminID)).findFirst().ifPresent(admin -> {
            admin.setAdminName(adminName);
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setAdminEmail(adminEmail);
            admin.setAdminAddress(adminAddress);
            admin.setAvatar(avatar);
            admin.setPageNumber(pageNumber);
            adminHistory history = new adminHistory("this admin", "Update Admin Account", admin.toString());
            adminHistories.add(history);
        });
        return "redirect:/ssps/admin/accountList";
    }

    // @PutMapping("/ssps/admin/updateSPSO/{spsoID}")
    // public String updateSPSO(@PathVariable String spsoID,
    // @RequestParam String spsoName,
    // @RequestParam String username,
    // @RequestParam String password,
    // @RequestParam String spsoEmail,
    // @RequestParam String spsoAddress,
    // @RequestParam String avatar) {
    // spsoList.stream().filter(s ->
    // s.getSPSOID().equals(spsoID)).findFirst().ifPresent(spso -> {
    // spso.setSPSOName(spsoName);
    // spso.setUsername(username);
    // spso.setPassword(password);
    // spso.setSPSOEmail(spsoEmail);
    // spso.setSPSOAddress(spsoAddress);
    // spso.setAvatar(avatar);
    // adminHistory history = new adminHistory("this admin", "Update SPSO Account",
    // spso.toString());
    // adminHistories.add(history);
    // });
    // return "redirect:/ssps/admin/accountList";
    // }

    @PutMapping("/ssps/admin/updateStudent/{studentID}")
    public String updateStudent(@PathVariable String studentID, @RequestParam String studentName,
            @RequestParam String username, @RequestParam String password, @RequestParam String studentEmail,
            @RequestParam String studentAddress, @RequestParam String avatar) {
        studentList.stream().filter(s -> s.getStudentID().equals(studentID)).findFirst().ifPresent(student -> {
            student.setStudentName(studentName);
            student.setUsername(username);
            student.setPassword(password);
            student.setStudentEmail(studentEmail);
            student.setStudentAddress(studentAddress);
            student.setAvatar(avatar);
            adminHistory history = new adminHistory("this admin", "Update Studednt Account", student.toString());
            adminHistories.add(history);
        });
        return "redirect:/ssps/admin/accountList";
    }

    // #endregion

    // #region delete account
    @DeleteMapping("/ssps/admin/deleteAdmin/{adminID}")
    public String deleteAdmin(@PathVariable String adminID) {
        Optional<Admin> admin = adminList.stream().filter(a -> a.getAdminID().equals(adminID)).findFirst();
        if (admin.isPresent()) {
            String actionDetail = admin.toString();
            if (adminList.removeIf(a -> a.getAdminID().equals(adminID))) {
                adminHistory history = new adminHistory("this admin", "Delete Admin Account", actionDetail);
                adminHistories.add(history);
            }
        }
        return "redirect:/ssps/admin/accountList";
    }

    // @PostMapping("/ssps/admin/deleteSPSO/{spsoID}")
    // public String deleteSPSO(@PathVariable String spsoID) {
    // Optional<SPSO> spso = spsoList.stream().filter(a ->
    // a.getSPSOID().equals(spsoID)).findFirst();
    // if (spso.isPresent()) {
    // String actionDetail = spso.toString();
    // if (spsoList.removeIf(spso -> spso.getSPSOID().equals(spsoID))) {
    // adminHistory history = new adminHistory("this admin", "Delete SPSO Account",
    // actionDetail);
    // adminHistories.add(history);
    // }
    // }
    // return "redirect:/ssps/admin/accountList";
    // }

    @DeleteMapping("/ssps/admin/deleteStudent/{studentID}")
    public String deleteStudent(@PathVariable String studentID) {
        Optional<Student> student = studentList.stream().filter(a -> a.getStudentID().equals(studentID)).findFirst();
        if (student.isPresent()) {
            String actionDetail = student.toString();
            if (studentList.removeIf(s -> s.getStudentID().equals(studentID))) {
                adminHistory history = new adminHistory("this admin", "Delete Student Account", actionDetail);
                adminHistories.add(history);
            }
        }
        return "redirect:/ssps/admin/accountList";
    }

    // #endregion
    // #endregion

}
