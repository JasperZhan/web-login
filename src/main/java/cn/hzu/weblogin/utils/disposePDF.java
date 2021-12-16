//package cn.hzu.weblogin.utils;
//
//import cn.hzu.weblogin.model.FormInfo;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
///**
// * @author Jasper Zhan
// * @date 2021年12月15日 17:19
// */
//public class disposePDF {
//
//    public static void disposePDF (FormInfo formInfo, HttpServletResponse response) {
//        com.itextpdf.text.Document document = new Document(PageSize.A4);
//        try {
//            //下面字体为windows自带的字体 在linux下可能会失效
//            Font font = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
//                    BaseFont.NOT_EMBEDDED));
//
//            ByteArrayOutputStream ba = new ByteArrayOutputStream();
//            PdfWriter.getInstance(document, response.getOutputStream());
//            document.open();
//
//            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//            com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
//
//
//            createPDFTitle(document);
//
//
//
//            Paragraph blankRow2 = new Paragraph(18f, " ", FontChinese11);
//            document.add(blankRow2);
//
//            PdfPTable table1 = new PdfPTable(2); //生成一个 两列的表格
//            document.add(createTitle(table1,font, products.size()));
//            document.add(blankRow2);
//
//            PdfPTable table = new PdfPTable(9);//生成一个 9列的表格
//            createTable(table,font);
//
//            PdfPCell cell;
//            int size = 18;
//            int count = 1;
//            int listSize = products.size();
//
//            for(int i = 0; i< listSize;i++) {
//                /**
//                 *  一页只有7行数据
//                 * 并且行数大于7时换一页，相当于每7行换一页
//                 * 当为不同部门时，换一页
//                 */
//                int num = count / 8;
//                if (num ==1) {
//
//                    //换下一页前必须把表格内容添加到文档中
//                    document.add(table);
//                    document.add(new Paragraph("填表人:         联系电话:",font));
//
//                    //换下一页
//                    document.newPage();
//
//                    createPDFTitle(document);
//                    document.add(blankRow2);
//
//                    PdfPTable table2 = new PdfPTable(2); //生成一个 两列的表格
//                    document.add(createTitle(table2,font,8));
//                    document.add(blankRow2);
//                    table = new PdfPTable(9);//生成一个 9列的表格
//                    createTable(table,font);
//                    count =1;
//                }
//
//                //序号
//                cell = new PdfPCell(new Phrase(Integer.valueOf(i+1).toString(),font));
//                cell.setFixedHeight(size); //设置高度
//                table.addCell(cell);
//
//                //银行卡号
//                cell = new PdfPCell(new Phrase(String.valueOf(products.get(i).getCard()),font));
//                cell.setFixedHeight(size);
//                table.addCell(cell);
//
//                //姓名
//                cell = new PdfPCell(new Phrase(String.valueOf(products.get(i).getName()),font));
//                cell.setFixedHeight(size);
//                table.addCell(cell);
//
//                //性别
//                cell = new PdfPCell(new Phrase(products.get(i).getSex()+"",font));
//                cell.setFixedHeight(size);
//                table.addCell(cell);
//
//                //现工作单位
//                cell = new PdfPCell(new Phrase(products.get(i).getWork_unit_and_position()+"",font));
//                cell.setFixedHeight(size);
//                table.addCell(cell);
//
//                //联系手机
//                cell = new PdfPCell(new Phrase(products.get(i).getPhone()+"",font));
//                cell.setFixedHeight(size);
//                table.addCell(cell);
//
//                //类别
//                cell = new PdfPCell(new Phrase(products.get(i).getApplication_category()+"",font));
//                cell.setFixedHeight(size);
//                table.addCell(cell);
//
//                //落户时间
//                cell = new PdfPCell(new Phrase(products.get(i).getSettlement_procedures_date()+"",font));
//                cell.setFixedHeight(size);
//                table.addCell(cell);
//
//                //申请金额
//                cell = new PdfPCell(new Phrase(products.get(i).getAmount_of_housing_application()+"",font));
//                cell.setFixedHeight(size);
//                table.addCell(cell);
//
//                count ++;
//            }
//
//            //当最后一页，或者第一页数据不足7条时，创建空单元格，补齐
//            //需要补齐的数量
//            int fillNum = 0;
//            if (listSize < 7) {
//                fillNum = 8 - listSize-1;
//            }else if (listSize > 7) {
//                fillNum = listSize % 7 ;
//                fillNum = 8 - fillNum - 3;
//            }
//
//            if (fillNum > 0) {
//                for (int i = 0; i< fillNum;i++) {
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//                    table.addCell(cell);
//
//
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("",font));
//                    cell.setFixedHeight(size); //设置高度
//                    table.addCell(cell);
//                }
//            }
//
//            System.out.println(listSize);
//            System.out.println(fillNum);
//
//            document.add(table);
//
//            document.add(new Paragraph("填表人:         联系电话:",font));
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }  finally {
//            document.close();
//        }
//
//    }
//
//
//    /**
//     * 生成pdf头部内容
//     */
////    public static void createPDFTitle(Document document ){
////        try {
////            BaseFont bf =  BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
////                    BaseFont.NOT_EMBEDDED);
////
////            Font font = new Font(bf);
////
////
////            //标题字体
////            Font titleFont = new Font(bf, 24F, Font.BOLD);
////            Paragraph p = new Paragraph("惠州市申报住房补贴汇总表",titleFont);
////            p.setAlignment(Element.ALIGN_CENTER);
////            document.add(p);
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//
//    public static PdfPTable createTable(PdfPTable table,Font font) throws IOException, DocumentException {
//
//        int headerwidths[] = {4, 9, 8, 4, 40, 11, 6, 12, 8}; // percentage
//        table.setWidths(headerwidths);
//        table.setWidthPercentage(100);
//        table.getDefaultCell().setPadding(3);
//        table.getDefaultCell().setBorderWidth(3);
//        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//
//        table.addCell(new PdfPCell(new Phrase("序号",font)));
//        table.addCell(new PdfPCell(new Phrase("建行卡号",font)));
//        table.addCell(new PdfPCell(new Phrase("姓名",font)));
//        table.addCell(new PdfPCell(new Phrase("性别",font)));
//        table.addCell(new PdfPCell(new Phrase("现工作单位及职务",font)));
//        table.addCell(new PdfPCell(new Phrase("联系手机",font)));
//        table.addCell(new PdfPCell(new Phrase("类别",font)));
//        table.addCell(new PdfPCell(new Phrase("落户时间",font)));
//        table.addCell(new PdfPCell(new Phrase("申请金额 （元）",font)));
//        return table;
//    }
//
//
//    public static PdfPTable createTitle(PdfPTable table,Font font, int num) throws IOException, DocumentException {
//
//        BaseFont bf =  BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
//                BaseFont.NOT_EMBEDDED);
//
//        int headerwidths[] = {16, 84}; // percentage
//        table.setWidths(headerwidths);
//        table.setWidthPercentage(100);
//        table.getDefaultCell().setPadding(3);
//        table.getDefaultCell().setBorderWidth(3);
//        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//
////        Font font1 = new Font(bf,10f, Font.BOLD);
////        table.addCell(new PdfPCell(new Phrase("    填表单位\n    审核意见",font1)));
////
////        Font font2 = new Font(bf, 12f,Font.NORMAL);
////        table.addCell(new PdfPCell(new Phrase("    经我单位审核，以下人才共" + num + "人，目前均在惠在岗工作，符合惠州市申报住房补贴条件，同意上报。        审核人:       。 2021年  月" +
////                "（加盖科室公章）" ,font2)));
//        return table;
//    }
//}