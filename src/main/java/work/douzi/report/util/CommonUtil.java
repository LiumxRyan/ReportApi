package work.douzi.report.util;

import com.alibaba.fastjson.JSONObject;
import work.douzi.report.config.CommonJsonException;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class CommonUtil {

    public static JSONObject resultJson(ResultCode resultCode){
        return resultJson(resultCode, new JSONObject());
    }

    public static JSONObject resultJson(ResultCode resultCode, Object info){
        JSONObject result = new JSONObject();
        result.put("code", resultCode.getResultCode());
        result.put("msg", resultCode.getResultMsg());
        result.put("info", info);
        return result;
    }

    public static JSONObject resultList(List<JSONObject> list){
        JSONObject result = resultJson(ResultCode.SUCCESS_200);
        JSONObject info = new JSONObject();
        info.put("list", list);
        result.put("info", info);
        return result;
    }

    public static JSONObject resultList(final JSONObject requestJson, List<JSONObject> list, int totalCount){
        int pageRow = requestJson.getIntValue("pageRow");
        int totalPage = getPageCounts(pageRow, totalCount);
        JSONObject result = resultJson(ResultCode.SUCCESS_200);
        JSONObject info = new JSONObject();
        info.put("list", list);
        info.put("totalCount", totalCount);
        info.put("totalPage", totalPage);
        result.put("info", info);
        return result;
    }

    private static int getPageCounts(int pageRow, int itemCount) {
        if (itemCount == 0) return 1;
        return itemCount % pageRow > 0 ? itemCount / pageRow + 1 : itemCount / pageRow;
    }

    public static JSONObject request2Json(HttpServletRequest request) {
        JSONObject requestJson = new JSONObject();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] pv = request.getParameterValues(paramName);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pv.length; i++) {
                if (pv[i].length() > 0) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(pv[i]);
                }
            }
            requestJson.put(paramName, sb.toString());
        }
        return requestJson;
    }

    public static void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
        if (!StringUtil.isNullOrEmpty(requiredColumns)) {
            String[] columns = requiredColumns.split(",");
            String missCol = "";
            for (String column : columns) {
                Object val = jsonObject.get(column.trim());
                if (StringUtil.isNullOrEmpty(val)) {
                    missCol += column + "  ";
                }
            }
            if (!StringUtil.isNullOrEmpty(missCol)) {
                jsonObject.clear();
                jsonObject.put("code", ResultCode.ERROR_1007.getResultCode());
                jsonObject.put("msg", ResultCode.ERROR_1007.getResultMsg()+":" + missCol.trim());
                jsonObject.put("info", new JSONObject());
                throw new CommonJsonException(jsonObject);
            }
        }
    }

    public static JSONObject convert2JsonAndCheckRequiredColumns(HttpServletRequest request, String requiredColumns) {
        JSONObject jsonObject = request2Json(request);
        hasAllRequired(jsonObject, requiredColumns);
        return jsonObject;
    }

    public static void fillPageParam(final JSONObject paramObject) {
        fillPageParam(paramObject, 10);
    }

    private static void fillPageParam(final JSONObject paramObject, int defaultPageRow) {
        int pageNum = paramObject.getIntValue("pageNum");
        pageNum = pageNum == 0 ? 1 : pageNum;
        int pageRow = paramObject.getIntValue("pageRow");
        pageRow = pageRow == 0 ? defaultPageRow : pageRow;
        paramObject.put("offSet", (pageNum - 1) * pageRow);
        paramObject.put("pageRow", pageRow);
        paramObject.put("pageNum", pageNum);
        //删除此参数,防止前端传了这个参数,pageHelper分页插件检测到之后,拦截导致SQL错误
        paramObject.remove("pageSize");
    }

}


