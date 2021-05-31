package work.douzi.report.config;

import com.alibaba.fastjson.JSONObject;
import work.douzi.report.util.CommonUtil;
import work.douzi.report.util.ResultCode;

public class CommonJsonException extends RuntimeException {
    private JSONObject resultJson;

    public CommonJsonException(ResultCode resultCode) {
        this.resultJson = CommonUtil.resultJson(resultCode);
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }
}
