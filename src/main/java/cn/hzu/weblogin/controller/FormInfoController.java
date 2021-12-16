package cn.hzu.weblogin.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hzu.weblogin.model.FormInfo;
import cn.hzu.weblogin.model.Form;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.service.FormInfoService;
import cn.hzu.weblogin.utils.ViewPDF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jasper Zhan
 * @since 2021-12-13
 */
@Slf4j

@Controller
public class FormInfoController {

    @Resource
    FormInfoService formInfoService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/form")
    public String formInfo(Model model) {
        Result<FormInfo> result = formInfoService.getInfoByUserId(StpUtil.getLoginIdAsInt());
        if (result.getData() != null) {
            model.addAttribute("form", result.getData());
        }
        return "form";
    }

    @ResponseBody
    @PostMapping(value = "form/save")
    public FormInfo saveInfo(@NotNull FormInfo formInfo) {
        System.out.println(StpUtil.getLoginIdAsInt());
        System.out.println(formInfo.toString());
        if (formInfo.getEducation().equals("研究生")) {
            formInfo.setEducation("1");
        } else {
            formInfo.setEducation("0");
        }
        if (formInfo.getAcademicDegree().equals("博士")) {
            formInfo.setAcademicDegree("2");
        } else if (formInfo.getAcademicDegree().equals("硕士")) {
            formInfo.setAcademicDegree("1");
        } else {
            formInfo.setAcademicDegree("0");
        }
        if (formInfo.getApplicationCategory().equals("博士")) {
            formInfo.setApplicationCategory("0");
        } else if (formInfo.getApplicationCategory().equals("博士后")) {
            formInfo.setApplicationCategory("2");
        } else {
            formInfo.setApplicationCategory("1");
        }
        if (formInfo.getSigningOfWorkContract().equals("已和企事业单位签订 3 年以上工作合同")) {
            formInfo.setSigningOfWorkContract("1");
        } else {
            formInfo.setSigningOfWorkContract("2");
        }

        formInfo.setUserId(StpUtil.getLoginIdAsInt());

        formInfoService.saveFormInfo(formInfo);
//        upload(formInfo);
        return formInfo;
    }

    @RequestMapping("/form/printPdf")
    public ModelAndView printPdf() throws Exception {
        FormInfo formInfo = formInfoService.getInfoByUserId(StpUtil.getLoginIdAsInt()).getData();
//        List<FormInfo> infoList = new ArrayList<>();
//        infoList.add(formInfo);
        Map<String, Object> model = new HashMap<>();
        model.put("formInfo", formInfo);
        return new ModelAndView(new ViewPDF(), model);
    }

    private String upload(FormInfo formInfo) {

        Form form = new Form();
        form.setSex(formInfo.getSex().equals("0") ? "男" : "女");
        form.setName(formInfo.getName());
        form.setWork_unit_and_position(formInfo.getWorkUnitAndPosition());
        form.setPhone(formInfo.getPhone());
        form.setApplication_category(formInfo.getApplicationCategory());
        form.setSettlement_procedures_date(formInfo.getSettlementProceduresDate().toString());
        form.setAmount_of_housing_application(formInfo.getAmountOfHousingApplication().intValue());

        String url = "http://192.168.43.36:8080/insertdata";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, form, String.class);
        System.out.println(responseEntity.getBody());

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(formInfo), headers);
//        String result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class).getBody();
//
//        Map<String, Object> map = new HashMap<>();
//        String url = "http://" + host + ":" + port + "/hello?name={name}";
//        map.put("name", name);
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);

//        String url = "http://" + "" + ":" + "" + "/user";
//        ResponseEntity<Form> responseEntity = restTemplate.postForEntity(url, form, Form.class);
//        System.out.println(responseEntity.getBody());
//
        return "1111111";
//        log.info("调用接口回参strBody：{}", result);
    }


}
