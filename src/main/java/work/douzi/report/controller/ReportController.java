package work.douzi.report.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import work.douzi.report.service.ReportService;
import work.douzi.report.util.CommonUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Ryan
 * @date 2021/6/1
 */
@RestController
@RequestMapping("report")
public class ReportController {

    @Resource
    private ReportService reportService;

    @GetMapping("list")
    public JSONObject list(HttpServletRequest request){
        return reportService.list(CommonUtil.request2Json(request));
    }

    @PostMapping("add")
    public JSONObject add(@RequestBody JSONObject requestJson) {
        return reportService.add(requestJson);
    }

    @GetMapping("export")
    public JSONObject export(HttpServletRequest request){
        return reportService.export(CommonUtil.request2Json(request));
    }

    @PostMapping("delete")
    public JSONObject delete(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "report_id");
        return reportService.delete(requestJson);
    }

    @PostMapping("edit")
    public JSONObject edit(@RequestBody JSONObject requestJson) {
        return reportService.edit(requestJson);
    }

}
