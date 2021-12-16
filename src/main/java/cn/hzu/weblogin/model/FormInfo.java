package cn.hzu.weblogin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jasper Zhan
 * @since 2021-12-13
 */

@Getter
@Setter
@TableName("application_form_info")
@ApiModel(value = "FormInfo对象", description = "")
public class FormInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("性别（0 男/ 1 女）")
    @TableField("sex")
    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("出生日期")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty("籍贯")
    @TableField("native_place")
    private String nativePlace;

    @ApiModelProperty("工作单位及职务")
    @TableField("work_unit_and_position")
    private String workUnitAndPosition;

    @ApiModelProperty("学历（0 大学本科/ 1 研究生）")
    @TableField("education")
    private String education;

    @ApiModelProperty("学位（0 学士/ 1 硕士/ 2 博士）")
    @TableField("academic_degree")
    private String academicDegree;

    @ApiModelProperty("专业")
    @TableField("major")
    private String major;

    @ApiModelProperty("技术职称")
    @TableField("technical_title")
    private String technicalTitle;

    @ApiModelProperty("家庭住址")
    @TableField("home_address")
    private String homeAddress;

    @ApiModelProperty("办公电话")
    @TableField("office_telephone")
    private String officeTelephone;

    @ApiModelProperty("申请类别（0 博士/ 1 正高职称/ 2 博士后）")
    @TableField("application_category")
    private String applicationCategory;

    @ApiModelProperty("手机")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("工作合同签订情况	1 已和企业事业单位签订3年以上工作合同	2 被机关事业单位接收录用")
    @TableField("signing_of_work_contract")
    private String signingOfWorkContract;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("在惠签约工作年限")
    @TableField("years_of_contract_signing_in_huizhou")
    private LocalDate yearsOfContractSigningInHuizhou;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("落户手续办理情况 日期")
    @TableField("settlement_procedures_date")
    private LocalDate settlementProceduresDate;

    @ApiModelProperty("落户手续办理情况 派出所")
    @TableField("settlement_procedures_local")
    private String settlementProceduresLocal;

    @ApiModelProperty("以申请住房补贴金额 （单位/元）")
    @TableField("amount_of_housing_application")
    private BigDecimal amountOfHousingApplication;

    @ApiModelProperty("申请理由和依据")
    @TableField("reasons_and_basis_for_application")
    private String reasonsAndBasisForApplication;

    @ApiModelProperty("申请住房补贴金额 大写")
    @TableField("amount_of_housing_applicationB")
    private String amountOfHousingApplicationB;


    @Override
    public String toString() {
        return "打印信息------------------------------->\n"
                + "id：" + id + "\n"
                + "用户id：" + userId + "\n"
                + "姓名：" + name + "\n"
                + "性别（0 男/ 1 女）：" + sex + "\n"
                + "出生日期：" + birthday + "\n"
                + "籍贯：" + nativePlace + "\n"
                + "工作单位及职务：" + workUnitAndPosition + "\n"
                + "学历（0 大学本科/ 1 研究生）：" + education + "\n"
                + "学位（0 学士/ 1 硕士/ 2 博士）：" + academicDegree + "\n"
                + "专业：" + major + "\n"
                + "技术职称：" + technicalTitle + "\n"
                + "家庭住址：" + homeAddress + "\n"
                + "办公电话：" + officeTelephone + "\n"
                + "申请类别（0 博士/ 1 正高职称/ 2 博士后）：" + applicationCategory + "\n"
                + "手机：" + phone + "\n"
                + "工作合同签订情况：1 已和企业事业单位签订3年以上工作合同，2 被机关事业单位接收录用：" + signingOfWorkContract + "\n"
                + "在惠签约工作年限：" + yearsOfContractSigningInHuizhou + "\n"
                + "落户手续办理情况日期：" + settlementProceduresDate + "\n"
                + "落户手续办理情况派出所：" + settlementProceduresLocal + "\n"
                + "以申请住房补贴金额（单位/元）：" + amountOfHousingApplication + "\n"
                + "申请理由和依据：" + reasonsAndBasisForApplication + "\n"
                + "打印完毕------------------------------->";
    }
}
