package com.software.ssps.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.software.ssps.Entity.Printer;
import com.software.ssps.Entity.Staff;
import com.software.ssps.Entity.Student;
import com.software.ssps.Entity.paymentHistory;
import com.software.ssps.Entity.printHistory;
import com.software.ssps.Entity.systemHistory;

@Controller
public class MainController {
    //////////////////// .......Hard coded data part.......////////////////////
    // Add hardcoded data list/ saving place here
    private List<Student> studentList;
    private List<paymentHistory> paymentHistory;
    private List<printHistory> printHistory;
    private List<Staff> staffList;
    private List<Printer> printerList;
    private List<systemHistory> systemHistories;

    // Write initializer in the Main Controller constructor
    public MainController() {
        studentList = new ArrayList<>();
        staffList = new ArrayList<>();
        printerList = new ArrayList<>();
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
        Staff staff1 = new Staff("123456", "Le Hoang", "hoang", "123456", "hoang@hcmut.edu.vn", "HCM", "hoang.png",
                "Admin");
        studentList.add(student1);
        studentList.add(student2);
        staffList.add(staff1);
        printerList.add(new Printer("P001", "Canon", "Tòa A4", "active"));
        printerList.add(new Printer("P002", "HP", "Tòa B2", "active"));
        printerList.add(new Printer("P003", "Epson", "Tòa C3", "inactive"));
        printerList.add(new Printer("P004", "Brother", "Tòa A1", "active"));
        printerList.add(new Printer("P005", "Samsung", "Tòa B5", "inactive"));

        paymentHistory = new ArrayList<>();
        printHistory = new ArrayList<>();
        systemHistories = new ArrayList<>();
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
    @GetMapping("/ssps/admin/accountList")
    public String accountList() {
        return "accountList";
    }

    @GetMapping("/ssps/admin/accountList/staffAccountList")
    @ResponseBody
    public List<Staff> staffAccountList() {
        return staffList;
    }

    @GetMapping("/ssps/admin/accountList/studentAccountList")
    @ResponseBody
    public List<Student> studentAccountList() {
        return studentList;
    }

    @PostMapping("/ssps/admin/addStaff")
    @ResponseBody
    public String addStaff(@RequestBody Staff staff) {
        // Staff newStaff = new Staff(name, id, username, password, email, address,
        // avatar);
        staffList.add(staff);
        return "redirect:/ssps/admin/accountList";
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/ssps/admin/staffInfo/{id}")
    @ResponseBody
    public Staff staffInfo(@PathVariable String id, Model model) {
        Optional<Staff> staff = staffList.stream().filter(a -> a.getID().equals(id)).findFirst();
        if (staff.isPresent()) {
            return staff.get();
        } else {
            return null;
        }
    }

    @PutMapping("/ssps/admin/updateStaff/{id}")
    @ResponseBody
    public String updateStaff(
            @PathVariable String id,
            @RequestBody Staff updateStaff) {
        staffList.stream().filter(a -> a.getID().equals(id)).findFirst().ifPresent(staff -> {
            staff.setName(updateStaff.getName());
            staff.setUsername(updateStaff.getUsername());
            staff.setPassword(updateStaff.getPassword());
            staff.setEmail(updateStaff.getEmail());
            staff.setAddress(updateStaff.getAddress());
            staff.setAvatar(updateStaff.getAvatar());
        });
        return "redirect:/ssps/admin/accountList";
    }

    @DeleteMapping("/ssps/admin/deleteStaff/{id}")
    @ResponseBody
    public String deleteStaff(@PathVariable String id) {
        Optional<Staff> staff = staffList.stream().filter(a -> a.getID().equals(id)).findFirst();
        if (staff.isPresent()) {
            staffList.removeIf(a -> a.getID().equals(id));
            return "success";
        }
        return "redirect:/ssps/admin/accountList";
    }

    // #endregion

    // #region printer

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/ssps/spso/printerList")
    @ResponseBody
    public List<Printer> printerList() {
        return printerList;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/ssps/spso/addPrinter")
    @ResponseBody
    public Printer addPrinter(@RequestBody Printer printer) {
        printerList.add(printer);
        return printer;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/ssps/spso/stopPrinter/{printerID}")
    @ResponseBody
    public String stopPrinter(@PathVariable String printerID) {
        printerList.stream().filter(p -> p.getPrinterID().equals(printerID)).findFirst().ifPresent(printer -> {
            printer.setStatus("inactive");
        });
        return "success";
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/ssps/spso/startPrinter/{printerID}")
    @ResponseBody
    public String startPrinter(@PathVariable String printerID) {
        printerList.stream().filter(p -> p.getPrinterID().equals(printerID)).findFirst().ifPresent(printer -> {
            printer.setStatus("active");
        });
        return "success";
    }
    // #endregion
}
