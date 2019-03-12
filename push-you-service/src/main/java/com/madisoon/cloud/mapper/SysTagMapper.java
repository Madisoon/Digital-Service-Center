package com.madisoon.cloud.mapper;

import com.madisoon.cloud.entity.SysTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zg
 * Description: 标签接口
 * @date 2:54 PM 2019/3/6
 **/

@Mapper
@Component
public interface SysTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysTag record);

    int insertSelective(SysTag record);

    SysTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysTag record);

    int updateByPrimaryKey(SysTag record);

    List<SysTag> listAllTag();

    List<Map> listTagIndex();
}