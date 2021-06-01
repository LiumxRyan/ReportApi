package work.douzi.report.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import work.douzi.report.service.UserService;
import work.douzi.report.util.CommonUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Ryan
 * @date 2021/6/1
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("login")
    public JSONObject login(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "user_name,password");
        return userService.login(requestJson);
    }

    @GetMapping("list")
    public JSONObject list(HttpServletRequest request){
        return userService.list(CommonUtil.request2Json(request));
    }

    @PostMapping("register")
    public JSONObject add(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "user_name,password");
        return userService.add(requestJson);
    }

    @GetMapping("logout")
    public JSONObject logout(HttpServletRequest request){
        return userService.logout(CommonUtil.request2Json(request));
    }

}
