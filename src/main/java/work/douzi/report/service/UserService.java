package work.douzi.report.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Ryan
 * @date 2021/6/1
 */
public interface UserService {

    /**
     * 登录
     * @param jsonObject
     * @return
     */
    JSONObject login(JSONObject jsonObject);

    /**
     * 退出
     * @param jsonObject
     * @return
     */
    JSONObject logout(JSONObject jsonObject);

    /**
     * 获取用户列表
     * @param jsonObject
     * @return
     */
    JSONObject list(JSONObject jsonObject);

    /**
     * 添加用户
     * @param jsonObject
     * @return
     */
    JSONObject add(JSONObject jsonObject);

}
