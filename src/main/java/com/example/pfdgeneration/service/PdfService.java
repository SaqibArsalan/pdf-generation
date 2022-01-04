package com.example.pfdgeneration.service;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {
    private static final String PDF_RESOURCES = "pdf-resources/";
    private StudentService studentService;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(StudentService studentService, SpringTemplateEngine templateEngine) {
        this.studentService = studentService;
        this.templateEngine = templateEngine;
    }

    public File generatePdf() throws IOException, DocumentException {
        Context context = getContext();
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    public File generatePdfWithParams(String htmlFile, Context context) throws IOException, DocumentException {
        String html = loadAndFillTemplateWithParams(htmlFile, context);
        return renderPdf(html);
    }


    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("students", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext() {
        Context context = new Context();
        context.setVariable("key-from-procurement-service", "value-from-procurement-service");
        context.setVariable("students", studentService.getStudents());
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("pdf_students", context);
    }

    private String loadAndFillTemplateWithParams(String html, Context context) {
        return templateEngine.process(html, context);
    }
}
