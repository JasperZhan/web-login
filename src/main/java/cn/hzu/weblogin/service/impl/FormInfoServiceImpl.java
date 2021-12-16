package cn.hzu.weblogin.service.impl;

import cn.hzu.weblogin.model.FormInfo;
import cn.hzu.weblogin.dao.FormInfoDao;
import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.service.FormInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jasper Zhan
 * @since 2021-12-13
 */
@Service
public class FormInfoServiceImpl extends ServiceImpl<FormInfoDao, FormInfo> implements FormInfoService {
    @Override
    public Result<FormInfo> saveFormInfo(FormInfo formInfo) {
        UpdateWrapper<FormInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", formInfo.getUserId());
        saveOrUpdate(formInfo, updateWrapper);
        Result<FormInfo> result = new Result<>();
        result.setResultSuccess("成功", formInfo);
        return result;
    }

    @Override
    public Result<FormInfo> getInfoByUserId(Integer user_id) {
        QueryWrapper<FormInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        FormInfo formInfo = getOne(queryWrapper);
        Result<FormInfo> result = new Result<>();
        result.setResultSuccess("成功", formInfo);
        return result;
    }
}
