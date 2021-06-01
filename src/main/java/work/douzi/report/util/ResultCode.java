package work.douzi.report.util;

/**
 * @author Ryan
 */
public enum ResultCode {

    SUCCESS_200("200", "请求成功"),
    SUCCESS_2001("2001", "添加成功"),
    SUCCESS_2002("2002", "删除成功"),
    SUCCESS_2003("2003", "查找成功"),
    SUCCESS_2004("2004", "修改成功"),
    SUCCESS_2005("2005", "登录成功"),
    SUCCESS_2006("2006", "导出成功"),
    SUCCESS_2007("2007", "注册成功"),
    SUCCESS_2008("2008", "退出成功"),
    ERROR_100("100", "请求失败"),
    ERROR_1001("1001", "添加失败"),
    ERROR_1002("1002", "删除失败"),
    ERROR_1003("1003", "查找失败"),
    ERROR_1004("1004", "修改失败"),
    ERROR_1005("1005", "用户不存在"),
    ERROR_1006("1006", "密码错误"),
    ERROR_1007("1007", "缺少必填参数"),
    ERROR_1008("1008", "导出失败"),
    ERROR_1009("1009", "注册失败"),
    ERROR_1010("1010", "退出失败"),
    ERROR_1011("1011", "用户已存在");


    private String resultCode;
    private String resultMsg;
    public String getResultCode() {
        return resultCode;
    }
    public String getResultMsg() {
        return resultMsg;
    }
    ResultCode(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}

