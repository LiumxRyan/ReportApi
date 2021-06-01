package work.douzi.report.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Ryan
 * @date 2021/6/1
 */
public interface ReportService {

    /**
     * 获取周报列表
     * @param jsonObject
     * @return
     */
    JSONObject list(JSONObject jsonObject);

    /**
     * 添加周报
     * @param jsonObject
     * @return
     */
    JSONObject add(JSONObject jsonObject);

    /**
     * 导出周报
     * @param jsonObject
     * @return
     */
    JSONObject export(JSONObject jsonObject);

    /**
     * 删除周报
     * @param jsonObject
     * @return
     */
    JSONObject delete(JSONObject jsonObject);

    /**
     * 编辑周报
     * @param jsonObject
     * @return
     */
    JSONObject edit(JSONObject jsonObject);

}
