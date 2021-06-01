package work.douzi.report.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Ryan
 * @date 2021/6/1
 */
@Mapper
public interface ReportDao {

    /**
     * 计数
     * @param jsonObject
     * @return
     */
    int count(JSONObject jsonObject);

    /**
     * 获取周报列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> list(JSONObject jsonObject);

    /**
     * 获取周报列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> list2(JSONObject jsonObject);

    /**
     * 添加周报
     * @param jsonObject
     * @return
     */
    int add(JSONObject jsonObject);

    /**
     * 删除周报
     * @param jsonObject
     * @return
     */
    int delete(JSONObject jsonObject);

    /**
     * 编辑周报
     * @param jsonObject
     * @return
     */
    int edit(JSONObject jsonObject);

}
