package cn.hzu.weblogin.service;

import cn.hzu.weblogin.model.FormInfo;
import cn.hzu.weblogin.model.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jasper Zhan
 * @since 2021-12-13
 */
public interface FormInfoService extends IService<FormInfo> {
    Result<FormInfo> saveFormInfo(FormInfo formInfo);

    Result<FormInfo> getInfoByUserId(Integer user_id);
}
