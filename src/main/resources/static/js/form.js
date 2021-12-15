$('#print').click(function () {
    $.ajax({
        //跳转 url
        url: "/print",
        type: "Get",
        datatype: "HTML",
        success: function (data) {
            alert(data)

        }
    })
});

$('#small').blur(function () {
    var money = $('#small').val();
    var transMoney = transCnMoney(money);
    $('#big').val(transMoney);
})

function getInfo() {
    var name = $('#name').val()
    var options = $('#sex option:selected');
    var sexStr = options.val();
    var sex
    if (sexStr === '男') {
        sex = 0
    } else if (sexStr === '女') {
        sex = 1
    }
    var inputDate = $('#inputDate').val()+"-01日";
    var str1 = inputDate.replace('年','-').replace('月','-').replace('日','');
    var options1 = $('#pro option:selected');
    var pro = options1.val();
    var options2 = $('#city option:selected');
    var city = options2.val();
    var nativePlace = pro + city;
    var workUnitAndPosition = $('#work').val();
    var options3 = $('#education option:selected');
    var education = options3.val();
    var options4 = $('#degree option:selected');
    var academicDegree = options4.val();
    var major = $('#major').val();
    var technicalTitle = $('#technical_title').val();
    var shi = $('#shi').val();
    var options5 = $('#qu option:selected');
    var qu = options5.val();
    var address = $('#address').val();
    var homeAddress = shi + qu + address;
    var officeTelephone = $('#phone').val();
    var applicationCategory = $("[name='category']:checked").val();
    var phone = $('#tel').val();
    var signingOfWorkContract = $("[name='work_status']:checked").val();
    var yearsOfContractSigningInHuizhou = $('#work_date').val()+"-01日";
    var str2 = inputDate.replace('年','-').replace('月','-').replace('日','');
    var settlementProceduresDate = $('#home_date').val()+"-01日";
    var str3 = inputDate.replace('年','-').replace('月','-').replace('日','');
    var settlementProceduresLocal = $('#location').val();
    var amountOfHousingApplication = $('#small').val();
    var amountOfHousingApplicationB = $('#big').val();
    var reasonsAndBasisForApplication = $('#reason').val();
    var para = {
        name: name,
        sex: sex,
        birthday: str1,
        nativePlace: nativePlace,
        workUnitAndPosition: workUnitAndPosition,
        education: education,
        academicDegree: academicDegree,
        major: major,
        technicalTitle: technicalTitle,
        homeAddress: homeAddress,
        officeTelephone: officeTelephone,
        applicationCategory: applicationCategory,
        phone: phone,
        signingOfWorkContract: signingOfWorkContract,
        yearsOfContractSigningInHuizhou: str2,
        settlementProceduresDate: str3,
        settlementProceduresLocal: settlementProceduresLocal,
        amountOfHousingApplication: amountOfHousingApplication,
        amountOfHousingApplicationB: amountOfHousingApplicationB,
        reasonsAndBasisForApplication: reasonsAndBasisForApplication,
    };
    return para;
}

$('#save_info').click(function () {
    var para = getInfo();
    if (para.name != null && para.sex != null && para.inputDate != null && para.nativePlace != null && para.workUnitAndPosition != null
        && para.education != null && para.academicDegree != null && para.major != null && para.technicalTitle != null
        && para.homeAddress != null && para.officeTelephone != null && para.applicationCategory != null && para.phone != null
        && para.signingOfWorkContract != null && para.yearsOfContractSigningInHuizhou != null && para.settlementProceduresDate != null
        && para.settlementProceduresLocal != null && para.amountOfHousingApplication != null
        && para.amountOfHousingApplicationB != null && para.reasonsAndBasisForApplication != null) {
        $.ajax({
            //跳转 url
            url: "/form/save",
            type: "Post",
            data: para,
            datatype: "HTML",
            success: function (data) {
                eval(data)
                console.log(data)
            }
        })
    } else {
        alert("字段不能为空，请仔细检查");
    }
})

function transCnMoney(number) {
    var CN_MONEY = "";
    var CN_UNIT = "仟佰拾亿仟佰拾万仟佰拾元角分";
    number = parseFloat(number).toFixed(2).toString();//浏览器默认为整数型,将数字转为2位有效数字的float类型再转为字符串
    var dot = number.indexOf('.');//将从小数点开始分开
    if (dot >= 0) {
        number = number.substring(0, dot) + number.substr(dot + 1, 2);
        CN_UNIT = CN_UNIT.substr(CN_UNIT.length - number.length);
        for (var i = 0; i < number.length; i++) {
            CN_MONEY += '零壹贰叁肆伍陆柒捌玖'.substr(number.substr(i, 1), 1) + CN_UNIT.substr(i, 1);
        }
        return CN_MONEY.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");
    } else {
    }
}
