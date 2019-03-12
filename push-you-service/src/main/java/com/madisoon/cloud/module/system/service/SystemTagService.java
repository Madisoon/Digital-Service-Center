package com.madisoon.cloud.module.system.service;

import com.madisoon.cloud.entity.SysTag;
import com.madisoon.cloud.mapper.SysTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * 系统标签服务层
 *
 * @author Msater Zg
 * @version 1.0
 * @date 2019/3/6 2:55 PM
 */

@Service
public class SystemTagService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SystemUserService.class);

    private final SysTagMapper sysTagMapper;

    @Autowired
    public SystemTagService(SysTagMapper sysTagMapper) {
        this.sysTagMapper = sysTagMapper;
    }

    public List<SysTag> listAllTag() {
        return sysTagMapper.listAllTag();
    }

    public List<Map> listTagIndex() {
        return sysTagMapper.listTagIndex();
    }
}
