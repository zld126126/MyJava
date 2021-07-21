package com.dongtech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongtech.entity.UserinfoEntity;
import com.dongtech.mapper.UserinfoMapper;
import com.dongtech.service.UserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dongbao
 * @since 2019-02-17
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, UserinfoEntity> implements UserinfoService {
    @Resource
    UserinfoMapper userinfoMapper;
    @Override
    public IPage<UserinfoEntity> selectPageExt(UserinfoEntity user, int page, int pageSize) {
        try {
            Page<UserinfoEntity> p = new Page<>(page, pageSize);
            p.setRecords(userinfoMapper.selectPageExt(p, user));
            return p;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public IPage<UserinfoEntity> selectPage(Page<UserinfoEntity> page, QueryWrapper<UserinfoEntity> queryWrapper) {
        try {
            IPage<UserinfoEntity> iPage = userinfoMapper.selectPage(page, queryWrapper);
            return iPage;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
