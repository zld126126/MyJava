package com.dongtech.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongtech.entity.UserinfoEntity;
import com.dongtech.service.impl.UserinfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dongbao
 * @since 2019-02-17
 */
@RestController
@RequestMapping("/userinfo-entity")
public class UserinfoController {
    @Autowired
    UserinfoServiceImpl userinfoService;

    //localhost:8080/userinfo-entity/getAllUser
    @RequestMapping("/getAllUser")
    public List getAllUser(){
        List<UserinfoEntity> ulist = userinfoService.list();
        return ulist;
    }
    //localhost:8080/userinfo-entity/getAllUserByPage
    @RequestMapping("/getAllUserByPage")
    public List getAllUserByPage(){
        UserinfoEntity user = new UserinfoEntity();
        int page=2;//当前页
        int pageSize=1;//页面接收数据大小
        IPage<UserinfoEntity> iPage = userinfoService.selectPageExt(user, page, pageSize);
        return iPage.getRecords();
    }

    //localhost:8080/userinfo-entity/getUserListByCondition
    @RequestMapping("/getUserListByCondition")
    public List getUserListByCondition(){
        //QueryWrapper<UserinfoEntity> queryWrapper = new QueryWrapper<>();
        //queryWrapper.lambda().eq(UserinfoEntity::getName, "dong");
        //List<UserinfoEntity> ulist = userinfoService.list(queryWrapper);

        QueryWrapper<UserinfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",20);
        queryWrapper.like("name","ong");
        List<UserinfoEntity> ulist = userinfoService.list(queryWrapper);
        return  ulist;
    }

    //localhost:8080/userinfo-entity/getUserListByConditionAndPage
    @RequestMapping("/getUserListByConditionAndPage")
    public List getUserListByConditionAndPage(){
        //QueryWrapper<UserinfoEntity> queryWrapper = new QueryWrapper<>();
        //queryWrapper.lambda().eq(UserinfoEntity::getName, "dong");
        //List<UserinfoEntity> ulist = userinfoService.list(queryWrapper);

        QueryWrapper<UserinfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",20);
        Page<UserinfoEntity>page = new Page<>();
        page.setSize(1);
        page.setPages(2);
        IPage<UserinfoEntity> iPage = userinfoService.selectPage(page, queryWrapper);
        return  iPage.getRecords();
    }
}
