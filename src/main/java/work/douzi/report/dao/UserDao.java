package work.douzi.report.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Ryan
 * @date 2021/6/1
 */
@Mapper
public interface UserDao {

    /**
     * 计数
     * @param jsonObject
     * @return
     */
    int count(JSONObject jsonObject);

    /**
     * 获取用户列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> list(JSONObject jsonObject);

    /**
     * 通过用户名获取用户
     * @param jsonObject
     * @return
     */
    JSONObject getByName(JSONObject jsonObject);

    /**
     * 添加用户
     * @param jsonObject
     * @return
     */
    int add(JSONObject jsonObject);

}
