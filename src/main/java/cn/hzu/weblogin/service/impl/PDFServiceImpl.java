package cn.hzu.weblogin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hzu.weblogin.model.FormInfo;
import cn.hzu.weblogin.service.FormInfoService;
import cn.hzu.weblogin.service.PDFService;
import cn.hzu.weblogin.utils.disposePDF;
import com.lowagie.text.pdf.PdfFormField;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jasper Zhan
 * @date 2021年12月15日 17:15
 */
public class PDFServiceImpl implements PDFService {

    @Resource
    FormInfoService formInfoService;

    @Override
    public void generator(int page, HttpServletResponse response) {
        try{
            // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
            String fileName ="惠州市申报住房补贴表.pdf";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            //打开浏览器窗口预览文件
            response.setHeader("Content-Disposition","filename=" + new String(fileName.getBytes(), "iso8859-1"));
            //直接下载
            //response.setHeader("Content-Disposition","attachment;filename=" + new String(fileName.getBytes(), "iso8859-1"));

            FormInfo formInfo = formInfoService.getInfoByUserId(StpUtil.getLoginIdAsInt()).getData();

            disposePDF.disposePDF(formInfo,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
