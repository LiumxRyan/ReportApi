package work.douzi.report.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import work.douzi.report.dao.UserDao;
import work.douzi.report.service.UserService;
import work.douzi.report.util.CommonUtil;
import work.douzi.report.util.JwtUtil;
import work.douzi.report.util.ResultCode;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ryan
 * @date 2021/6/1
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public JSONObject login(JSONObject jsonObject){
        JSONObject user = userDao.getByName(jsonObject);
        if(user == null)
            return CommonUtil.resultJson(ResultCode.ERROR_1005);
        if(!jsonObject.getString("password").equals(user.getString("password")))
            return CommonUtil.resultJson(ResultCode.ERROR_1006);
        user.remove("password");
        user.put("token", JwtUtil.createToken(user));
        return CommonUtil.resultJson(ResultCode.SUCCESS_2005,user);
    }

    @Override
    public JSONObject logout(JSONObject jsonObject){
        return CommonUtil.resultJson(ResultCode.SUCCESS_2008);
    }

    @Override
    public JSONObject list(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = userDao.count(jsonObject);
        List<JSONObject> list = userDao.list(jsonObject);
        return CommonUtil.resultList(jsonObject, list, count);
    }

    @Override
    public JSONObject add(JSONObject jsonObject){
        if(userDao.getByName(jsonObject) != null) {
            return CommonUtil.resultJson(ResultCode.ERROR_1011);
        }
        if(userDao.add(jsonObject) == 1) {
            return CommonUtil.resultJson(ResultCode.SUCCESS_2007);
        }
        return CommonUtil.resultJson(ResultCode.ERROR_1009);
    }

}
