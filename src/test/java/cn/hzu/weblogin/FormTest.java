package cn.hzu.weblogin;

import cn.hzu.weblogin.model.FormInfo;
import cn.hzu.weblogin.model.Form;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.service.FormInfoService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jasper Zhan
 * @date 2021年12月15日 8:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FormTest {

    @Resource
    FormInfoService formInfoService;

    @Resource
    RestTemplate restTemplate;

    @Test
    public void getTest() {
        Result<FormInfo> result = formInfoService.getInfoByUserId(26);
        FormInfo formInfo = result.getData();
        System.out.println(formInfo.toString());
    }

    @Test
    public void sentTest() {
        Result<FormInfo> result = formInfoService.getInfoByUserId(26);
        FormInfo formInfo = result.getData();
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
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//
//        //设置接收返回值的格式为json
//        List<MediaType> mediaTypeList = new ArrayList<>();
//        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
//        headers.setAccept(mediaTypeList);
//
//        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
//
//        ResponseEntity<T> responseEntity;
//
//        System.out.println(requestJson);
//
//        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);

//        return responseEntity;


//        Map<String, Object> map = new HashMap<>();
//        String url = "http://192.168.43.36:8080/insertdata";
//        map.put("form", form);
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);

    }
}
