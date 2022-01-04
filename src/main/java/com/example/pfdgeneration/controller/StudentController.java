package com.example.pfdgeneration.controller;

import com.example.pfdgeneration.service.PdfService;
import com.example.pfdgeneration.service.StudentService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class StudentController {
    private StudentService studentService;
    private PdfService pdfService;

    @Autowired
    public StudentController(StudentService studentService, PdfService pdfService) {
        this.studentService = studentService;
        this.pdfService = pdfService;
    }

    @GetMapping("/students")
    public ModelAndView studentsView(ModelAndView modelAndView) {
        modelAndView.addObject("students", studentService.getStudents());
        modelAndView.setViewName("students");
        return modelAndView;
    }

    @GetMapping("/download-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void downloadPDF(HttpServletResponse response, String htmlFile, Context context) {
        try {
            Path file = Paths.get(pdfService.generatePdfWithParams(htmlFile, context).getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }


}
