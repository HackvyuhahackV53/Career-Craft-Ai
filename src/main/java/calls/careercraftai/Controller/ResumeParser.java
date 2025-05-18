package calls.careercraftai.Controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class ResumeParser {

    public static String parse(MultipartFile file) throws Exception {
        String content = "";

        if (file.getOriginalFilename().endsWith(".pdf")) {
            try (InputStream in = file.getInputStream();
                 PDDocument document = PDDocument.load(in)) {
                PDFTextStripper stripper = new PDFTextStripper();
                content = stripper.getText(document);
            }
        } else if (file.getOriginalFilename().endsWith(".docx")) {
            // Implement DOCX parsing with Apache POI
            content = DocxParser.parseDocx(file.getInputStream());
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }

        return content;
    }
}

