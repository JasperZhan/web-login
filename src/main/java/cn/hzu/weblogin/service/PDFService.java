package cn.hzu.weblogin.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Jasper Zhan
 * @date 2021年12月15日 17:14
 */
public interface PDFService {
    public void generator(int page, HttpServletResponse response);
}
