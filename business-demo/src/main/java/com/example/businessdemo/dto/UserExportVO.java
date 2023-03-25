package com.example.businessdemo.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author zhaolei
 * @Date: 2022/9/25 0025 20:29
 * @Description:
 */
@Data
public class UserExportVO implements Serializable {
    private static final long serialVersionUID = -5644799954031156649L;

    @Excel(name = "姓名")
    private String realName;

    @Excel(name = "性别", replace = { "男_1", "女_2" }, suffix = "生")
    private Integer sex;

    @Excel(name = "出生日期", format = "yyyy-MM-dd")
    private Date birthday;

    @Excel(name = "手机号码")
    private String phone;

    @Excel(name = "固定电话")
    private String tel;

    @Excel(name = "邮箱")
    private String email;

    @Excel(name = "头像地址")
    private String avatar;
}
