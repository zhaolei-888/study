package com.example.businessdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.businessdemo.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
