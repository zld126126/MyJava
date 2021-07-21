package com.dongtech.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongtech.entity.UserinfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dongbao
 * @since 2019-02-17
 */
@Mapper
public interface UserinfoMapper extends BaseMapper<UserinfoEntity> {
    @Select("select * from userinfo")
    List<UserinfoEntity> selectPageExt(Page<UserinfoEntity> p, UserinfoEntity user);
}
