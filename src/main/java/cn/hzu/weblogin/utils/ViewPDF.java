package cn.hzu.weblogin.utils;


import cn.hzu.weblogin.model.FormInfo;
//import com.lowagie.text.Document;
//import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jasper Zhan
 * @date 2021年12月14日 20:12
 */
public class ViewPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = new Date().getTime()+"_form.pdf";

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "filename=" + new String(fileName.getBytes(), "iso8859-1"));
        FormInfo formInfo = (FormInfo) model.get("formInfo");
        PrintPDF printPDF = new PrintPDF();
        printPDF.createPDF(document, writer, formInfo);
    }
}
