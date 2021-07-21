package com.dongtech.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongtech.entity.UserinfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dongbao
 * @since 2019-02-17
 */
public interface UserinfoService extends IService<UserinfoEntity> {

    IPage<UserinfoEntity> selectPageExt(UserinfoEntity user, int page, int pageSize);

    IPage<UserinfoEntity> selectPage(Page<UserinfoEntity> page, QueryWrapper<UserinfoEntity> queryWrapper);
}
