package cn.hzu.weblogin.utils;

import cn.hzu.weblogin.model.FormInfo;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Jasper Zhan
 * @date 2021年12月13日 17:01
 */
public class PrintPDF {
    public static PdfPTable createTable(PdfWriter writer, FormInfo formInfo) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(8);//生成一个八列的表格
        PdfPCell cell;
        int size = 20;
        cell = new PdfPCell(new Phrase("姓名"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getName()));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("性别"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getSex()));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("出生年月"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getBirthday().toString()));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("籍贯"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getNativePlace()));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("工作单位及职务"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getWorkUnitAndPosition()));
        cell.setColspan(7);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("学历"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getEducation()));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("学位"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getAcademicDegree()));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("专业"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getMajor()));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("技术职称"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getTechnicalTitle()));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("家庭住址（惠州）"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getHomeAddress()));
        cell.setColspan(4);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("办公电话"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getOfficeTelephone()));
        cell.setColspan(2);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("申请类别"));
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        Phrase phrase1 = new Phrase();
        Chunk chunk1 = new Chunk("         博士");
        Chunk chunk2 = new Chunk("         正高职称");
        Chunk chunk3 = new Chunk("         博士后");
        phrase1.add(chunk1);
        phrase1.add(chunk2);
        phrase1.add(chunk3);
        cell = new PdfPCell(phrase1);
        cell.setColspan(4);
        cell.setRowspan(1);
        table.addCell(cell);
        //增加三个单选框
        PdfFormField radiogroup = PdfFormField.createRadioButton(writer, true);
        radiogroup.setFieldName("salesModel");
        Rectangle rect1 = new Rectangle(110, 722, 120, 712);
        Rectangle rect2 = new Rectangle(165, 722, 175, 712);
        Rectangle rect3 = new Rectangle(185, 722, 200, 712);
        RadioCheckField radio1 = new RadioCheckField(writer, rect1, null, "self-support" ) ;
        RadioCheckField radio2 = new RadioCheckField(writer, rect2, null, "cooprate") ;
        RadioCheckField radio3 = new RadioCheckField(writer, rect3, null, "cooprate") ;
        switch (formInfo.getApplicationCategory()) {
            case "0": radio1.setChecked(true); break;
            case "1": radio2.setChecked(true); break;
            case "2": radio3.setChecked(true); break;
        }
        PdfFormField radiofield1 = radio1.getRadioField();
        PdfFormField radiofield2 = radio2.getRadioField();
        PdfFormField radiofield3 = radio3.getRadioField();
        radiogroup.addKid(radiofield1);
        radiogroup.addKid(radiofield2);
        radiogroup.addKid(radiofield3);
        writer.addAnnotation(radiogroup);
        cell = new PdfPCell(new Phrase("手机"));
        cell.setColspan(1);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(formInfo.getPhone()));
        cell.setColspan(4);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("工作合同签订情况"));
        cell.setColspan(1);
        cell.setRowspan(2);
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("   已和企业事业单位签订3年以上工作合同\n"
                + "被机关事业单位接收录用"));
        cell.setColspan(7);
        cell.setRowspan(2);
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("在惠签约工作年限"));
        cell.setColspan(1);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(""));
        cell.setColspan(7);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("落户手续办理情况"));
        cell.setColspan(1);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(""));
        cell.setColspan(7);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("申请住房补贴金额"));
        cell.setColspan(1);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(""));
        cell.setColspan(7);
        cell.setFixedHeight(size);
        cell.setRowspan(1);
        table.addCell(cell);
        return table;
    }

    public void createPDF (Document document, PdfWriter writer, FormInfo formInfo) throws IOException {
        try {
            document.addTitle("惠州市高级人才住房补贴申领表");
            document.addAuthor("惠州市政府");
            document.addSubject("form info.4");
            document.addKeywords("info");
            document.open();
            PdfPTable table = createTable(writer, formInfo);
            document.add(table);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
