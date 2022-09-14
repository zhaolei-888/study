package com.example.businessdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user_group")//指定表名
public class UserGroup implements Serializable {
    private static final long serialVersionUID = -5644799954031156649L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;
}
