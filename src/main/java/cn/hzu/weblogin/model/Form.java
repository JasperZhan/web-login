package cn.hzu.weblogin.model;

import lombok.Data;

/**
 * @author Jasper Zhan
 * @date 2021年12月15日 8:11
 */
@Data
public class Form {
    int id;
    String card;
    String name;
    String sex;
    String work_unit_and_position;
    String phone;
    String application_category;
    String settlement_procedures_date;
    int amount_of_housing_application;
}