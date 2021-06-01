package work.douzi.report.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import work.douzi.report.dao.ReportDao;
import work.douzi.report.service.ReportService;
import work.douzi.report.util.CommonUtil;
import work.douzi.report.util.ExportExcel;
import work.douzi.report.util.ResultCode;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Ryan
 * @date 2021/6/1
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportDao reportDao;

    @Override
    public JSONObject list(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = reportDao.count(jsonObject);
        List<JSONObject> list = reportDao.list(jsonObject);
        return CommonUtil.resultList(jsonObject, list, count);
    }

    @Override
    public JSONObject add(JSONObject jsonObject){
        if(reportDao.add(jsonObject) == 1)
            return CommonUtil.resultJson(ResultCode.SUCCESS_2001);
        return CommonUtil.resultJson(ResultCode.ERROR_1001);
    }

    @Override
    public JSONObject export(JSONObject jsonObject){
        List<JSONObject> list = reportDao.list2(jsonObject);
        String excelName = "资源导出";
        String[] headers = new String[]{"ID","标题","本周成果","问题"};
        try{
            OutputStream out = new FileOutputStream("C:\\Users\\Ryan\\Documents\\a.xls");
            ExportExcel.exportExcel(excelName,headers,list,out,"yyyy-MM-dd");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.resultJson(ResultCode.ERROR_1008);
        }
        return CommonUtil.resultJson(ResultCode.SUCCESS_2006);
    }

    @Override
    public JSONObject delete(JSONObject jsonObject){
        if(reportDao.delete(jsonObject) == 1)
            return CommonUtil.resultJson(ResultCode.SUCCESS_2002);
        return CommonUtil.resultJson(ResultCode.ERROR_1002);
    }

    @Override
    public JSONObject edit(JSONObject jsonObject){
        if(reportDao.edit(jsonObject) == 1) {
            return CommonUtil.resultJson(ResultCode.SUCCESS_2004);
        }
        return CommonUtil.resultJson(ResultCode.ERROR_1004);
    }

}
