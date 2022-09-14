package com.example.businessdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.businessdemo.model.UserGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {
}
