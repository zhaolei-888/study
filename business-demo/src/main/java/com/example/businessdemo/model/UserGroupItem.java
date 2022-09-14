package com.example.businessdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@TableName(value = "user_group_item")//指定表名
public class UserGroupItem implements Serializable {
    private static final long serialVersionUID = -5644799954031156649L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userGroupId;

    private Long userId;
}
