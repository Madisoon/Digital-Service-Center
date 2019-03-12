package com.madisoon.cloud.mapper;

import com.madisoon.cloud.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Description:
 * 用户表
 *
 * @author Msater Zg
 * @version 1.0
 * @date 2019/2/15 3:08 PM
 */

@Mapper
@Component
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
    * @author zg
    * Description: 根据账号获取用户信息
    * @date 4:29 PM 2019/3/5
    * @param account 账号
    * @return com.madisoon.cloud.entity.SysUser
    **/
    SysUser getUserByAccount(String account);
}