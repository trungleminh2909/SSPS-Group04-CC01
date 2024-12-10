package com.software.ssps.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.software.ssps.Entity.Student;
import com.software.ssps.Entity.paymentHistory;
import com.software.ssps.Entity.paymentProperties;
import com.software.ssps.Entity.paymentProperties.paymentStatus;
import com.software.ssps.Entity.printHistory;
import com.software.ssps.Entity.printHistory.PrintStatus;
import com.software.ssps.Entity.printProperties;
import com.software.ssps.Services.Student.PaymentService;
import com.software.ssps.Services.Student.PrintPropertyService;
import com.software.ssps.Services.Student.PrintService;
import com.software.ssps.Services.Student.StudentService;
@CrossOrigin
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
                150, // pageNumber
                new ArrayList<>() // uploadedFiles (empty list for now)
        );
        studentList.add(student1);
        studentList.add(student2);

        paymentHistory = new ArrayList<>();
        printHistory = new ArrayList<>();
    }

    //////////////////// .......Hard coded data part.......////////////////////

    //////////////////// .......Autowired Service
    //////////////////// Initializer.......////////////////////
    @Autowired
    private StudentService StudentService;

    @Autowired
    private PaymentService PaymentService;

    @Autowired
    private PrintService PrintService;

    @Autowired
    private PrintPropertyService PrintPropertyService;

    //////////////////// .......Autowired Service.......////////////////////
    @GetMapping("/ssps/homepage")
    public String mainpage() {
        // Go to homepage of the ssps web
        return "homepage";
    }

    @GetMapping("/ssps/login")
    public String login() {
        // Navigate to the login session
        return "login";
    }

    @PostMapping("/ssps/login")
    public ResponseEntity<?> validateLogin(
            @RequestBody Map<String, String> request,
            Model model) {

        String username = request.get("username");
        String password = request.get("password");
        String loginOption = request.get("loginOption");

        if ("SPSO".equals(loginOption)) {
            if ("spsoUser".equals(username) && "spsoPass".equals(password)) {
                // To do: SPSO homepage here
                return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "SPSO login successful",
                    "username", username,
                    "role", "SPSO"
                ));
            }
        } else if ("HCMUT".equals(loginOption)) {
            for (Student student : studentList) {
                if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                    // Redirect to student home page with the student ID
                    return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "HCMUT login successful",
                        "username", student.getUsername(),
                        "role", "HCMUT",
                        "studentId", student.getStudentID(),
                        "studentName", student.getStudentName(),
                        "studentEmail", student.getStudentEmail(),
                        "studentAddress", student.getStudentAddress(),
                        "pageNumber", student.getPageNumber()
                    ));
                }
            }
        }
        // Invalid login handling
        
        model.addAttribute("error", "Invalid credentials or login option.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
            "error", "Invalid credentials or login option"
        ));
    }

    @GetMapping("/ssps/logout")
    public String logout(Model model) {
        // Return to the ssps homepage
        return "redirect:/ssps/homepage";
    }

    @GetMapping("/ssps/{studentID}/home")
    public String home(@PathVariable String studentID, Model model) {
        // Find the student in database from studentID and pass data to front end
        Student curStudent = StudentService.findById(studentID, studentList);
        model.addAttribute("studentName", curStudent.getStudentName());
        model.addAttribute("studentAvatar", curStudent.getAvatar());
        model.addAttribute("studentID", curStudent.getStudentID());
        model.addAttribute("pageNumber", curStudent.getPageNumber());
        // Go to homepage of student
        return "studentHome";
    }

    @GetMapping("/ssps/{studentID}/info")
    public String getStudentInfo(@PathVariable String studentID, Model model) {
        // Find the student in database from studentID and pass data to front end
        Student curStudent = StudentService.findById(studentID, studentList);
        model.addAttribute("studentName", curStudent.getStudentName());
        model.addAttribute("studentID", curStudent.getStudentID());
        model.addAttribute("studentAvatar", curStudent.getAvatar());
        model.addAttribute("email", curStudent.getStudentEmail());
        model.addAttribute("address", curStudent.getStudentAddress());
        model.addAttribute("pageNumber", curStudent.getPageNumber());
        // Go to the info page of student
        return "studentInfo";
    }

    @GetMapping("/ssps/{studentID}/print")
    public String print(@PathVariable String studentID, Model model) {
        // Pass the student to front end
        Student curStudent = StudentService.findById(studentID, studentList);
        model.addAttribute("currentStudent", curStudent);
        // Create list of print property and pass to front end to store print properties
        List<printProperties> fileList = new ArrayList<>();
        model.addAttribute("fileList", fileList);
        // Go to the print view of student
        return "studentPrint";
    }

    @PostMapping("/ssps/{studentID}/print")
    public ResponseEntity<?> printHandle(@PathVariable String studentID, 
            @RequestBody Map<String, String> request,
            RedirectAttributes redirectAttributes) {

        List<printProperties> fileList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (Integer i = 0; i < 1; i++) {
            String fileName = request.get("fileName");
            printProperties.pageType pageType = Integer.parseInt(request.get("pageType")) == 1 ? printProperties.pageType.A3 : printProperties.pageType.A4;
            String printerID = request.get("printerID");
            Boolean twoFace = Boolean.parseBoolean(request.get("twoFace"));
            Boolean color = Boolean.parseBoolean(request.get("color"));
            List<String> stringList = new ArrayList<>();
            try {
                // Convert JSON string to List<String>
                stringList = objectMapper.readValue(request.get("pageToPrint"), List.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<Integer> pageToPrint = new ArrayList<>();
            for (String s : stringList) {
                pageToPrint.add(Integer.parseInt(s));
            }
            System.out.println("Page List: " + pageToPrint);

            Integer numberOfCopy = Integer.parseInt(request.get("numberOfCopy"));

            printProperties temp = new printProperties(pageType, printerID, fileName, twoFace, color, pageToPrint, numberOfCopy);
            fileList.add(temp);
        }
        Integer cost = 0;
        for (printProperties file : fileList) {
            cost = cost + PrintPropertyService.getCost(file);
            System.out.println(file);
        }
        Student curStudent = StudentService.findById(studentID, studentList);
        System.out.println(curStudent.getPageNumber());
        // Check page balance if enough or not
        if (cost > curStudent.getPageNumber()) {
            redirectAttributes.addFlashAttribute("error", "Student does not have enough page balance");
            return ResponseEntity.ok(Map.of(
                "status", "failed",
                "message", "Not enough balance"
            ));
        } else {
            // Save to print History
            for (printProperties file : fileList) {
                Integer currentIndex = printHistory.size();
                LocalDate currentDate = LocalDate.now();
                String Note = "This is a print record";
                printHistory.add(new printHistory(PrintStatus.PRINTING, "PrintID " + currentIndex,
                        curStudent.getStudentID(), file.getFileName(), currentDate, Note));
            }
            redirectAttributes.addFlashAttribute("cost", cost);
            redirectAttributes.addFlashAttribute("currentStudent", curStudent);
            System.out.println(printHistory);
        }
        // Redirect to the page to show print request result
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Request accepted"
        ));
    }

    @GetMapping("/ssps/{studentID}/print-result")
    public String showPrintRequestResult(@PathVariable String studentID,
            @ModelAttribute("cost") Integer cost,
            @ModelAttribute("currentStudent") Student currentStudent,
            @ModelAttribute("error") String error,
            Model model) {
        // Add information to front end if exist
        if (cost != null) {
            model.addAttribute("cost", cost);
        }
        if (currentStudent != null) {
            model.addAttribute("currentStudent", currentStudent);
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        // Add studentID for routing
        model.addAttribute("studentID", studentID);
        // Page to show print request result
        return "printResult";
    }

    @GetMapping("ssps/{studentID}/print-history")
    public String history(@PathVariable String studentID, Model model) {
        // Find the student and their print History in database from studentID and pass
        // data to front end
        Student curStudent = StudentService.findById(studentID, studentList);
        List<printHistory> studentPrintHistory = new ArrayList<>();
        studentPrintHistory = PrintService.findById(curStudent, printHistory);
        model.addAttribute("currentStudent", curStudent);
        model.addAttribute("studentPrintHistory", studentPrintHistory);
        // Go to the print history page of the student
        return "printHistory";
    }

    @PostMapping("ssps/{studentID}/print-history")
    public ResponseEntity<?> history(
        // @PathVariable String studentID,
        @RequestBody Map<String, String> request) {

        // Find the student in the database
        String studentID = request.get("studentID");

        // Find the student and their print History in database from studentID and pass
        // data to front end
        Student curStudent = StudentService.findById(studentID, studentList);
        List<printHistory> studentPrintHistory = new ArrayList<>();
        studentPrintHistory = PrintService.findById(curStudent, printHistory);

        for (printHistory entry : studentPrintHistory) {
            System.out.println(entry);
        }
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "student", curStudent,
            "printHistory", studentPrintHistory
        ));
    }

    @GetMapping("ssps/{studentID}/payment")
    public String payment(@PathVariable String studentID, Model model) {
        Student curStudent = StudentService.findById(studentID, studentList);
        // Create a temporary payment property object to modify in front end
        paymentProperties payment = new paymentProperties(null, "paymentID", studentID,
                null, 0, 0, "receiptImagePath");
        model.addAttribute("payment", payment);
        model.addAttribute("currentStudent", curStudent);
        return "studentPayment";
    }

    @PostMapping("ssps/{studentID}/payment")
    public ResponseEntity<?> processPayment(
            @PathVariable String studentID,
            @RequestBody Map<String, String> request) {
        
        
        paymentProperties payment = new paymentProperties(paymentStatus.COMPLETED, request.get("paymentID"), studentID, LocalDate.now(), 
                                                        Integer.parseInt(request.get("pageBought")), Integer.parseInt(request.get("paymentAmount")), request.get("receipt"));

        Student curStudent = StudentService.findById(studentID, studentList);
        if (payment.getPaymentStatus().equals(paymentStatus.COMPLETED)) {
            // Create new record and add the payment history record to the list if payment
            // success/complete
            Integer currentIndex = paymentHistory.size();
            LocalDate currentDate = LocalDate.now();
            String Note = "This is a payment history record";
            paymentHistory
                    .add(new paymentHistory(paymentStatus.COMPLETED, "paymentID" + currentIndex, studentID, currentDate,
                            payment.getPageBought(), payment.getPaymentAmount(), payment.getReceipt(), Note));
            // Modify student page balance
            for (paymentHistory entry : paymentHistory) {

                System.out.println(entry);
            }
            curStudent.setPageNumber(curStudent.getPageNumber() + payment.getPageBought());
            // Add other attribute for redirect
            // redirectAttributes.addFlashAttribute("currentStudent", curStudent);
            return ResponseEntity.ok(Map.of(
                "status", "success"
            ));
        } else // Only add error in redirect
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
            "error", "Payment cancelled"
        ));
    }

    @GetMapping("ssps/{studentID}/payment-process")
    public String showPaymentResult(@PathVariable String studentID, Model model,
            @ModelAttribute("currentStudent") Student curStudent, @ModelAttribute("error") String error) {
        if (curStudent != null) {
            model.addAttribute("currentStudent", curStudent);
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        // Add studentID for routing
        model.addAttribute("studentID", studentID);
        // Page to show payment result
        return "paymentResult";
    }

    @GetMapping("ssps/{studentID}/payment-history")
    public String paymentHistory(@PathVariable String studentID, Model model) {
        // Find the student and their payment History in database from studentID and
        // pass
        // data to front end
        Student curStudent = StudentService.findById(studentID, studentList);
        List<paymentHistory> studentPaymentHistory = new ArrayList<>();
        studentPaymentHistory = PaymentService.findById(curStudent, paymentHistory);
        model.addAttribute("currentStudent", curStudent);
        model.addAttribute("studentPrintHistory", studentPaymentHistory);
        // Go to the print history page of the student
        return "paymentHistory";
    }

    @PostMapping("ssps/{studentID}/payment-history")
    public ResponseEntity<?> paymentHistory(
        @PathVariable String studentID,
        @RequestBody Map<String, String> request) {

        // Find the student in the database
        // String studentID = request.get("studentID");
        Student curStudent = StudentService.findById(studentID, studentList);
        
        if (curStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "error",
                    "message", "Student not found"
                ));
        }

        // Retrieve the student's payment history
        List<paymentHistory> studentPaymentHistory = new ArrayList<>();
        studentPaymentHistory = PaymentService.findById(curStudent, paymentHistory);

        // Return JSON response
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "student", curStudent,
            "paymentHistory", studentPaymentHistory
        ));
    }
    

}
