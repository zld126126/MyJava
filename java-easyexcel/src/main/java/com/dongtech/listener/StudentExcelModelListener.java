package com.dongtech.listener;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.dongtech.entity.StudentExcelModel;

public class StudentExcelModelListener extends AnalysisEventListener<StudentExcelModel> {

    @Override
    //每解析一行调用一次invoke方法
    public void invoke(StudentExcelModel studentExcelModel, AnalysisContext analysisContext) {
        System.out.println("当前行："+analysisContext.getCurrentRowNum());
        System.out.println(studentExcelModel);
    }

    @Override
    //解析结束后自动调用
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
