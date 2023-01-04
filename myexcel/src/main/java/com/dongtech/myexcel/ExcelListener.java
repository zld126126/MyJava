package com.dongtech.myexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<Map<Integer, String>> {
    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    private List<Map<Integer, String>> cachedDataList = new ArrayList<>();

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        logger.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        //logger.info("{}条数据，开始存储数据库！", cachedDataList.size());
        //logger.info("存储数据库成功！");
    }

    public List<String> GetHead(){
        List<String> headList = new ArrayList<>();

        Map<Integer, String> map = cachedDataList.get(0);
        for (int i = 0;i<map.size();i++){
            String title = map.get(i);
            headList.add(title);
        }
        return headList;
    }

    public List<List<String>> GetBody(){
        List<List<String>> bodyList = new ArrayList<>();
        for(int i = 1;i < cachedDataList.size();i++){
            List<String> bodyList2 = new ArrayList<>();
            Map<Integer, String> map = cachedDataList.get(i);
            for (int j = 0;j<map.size(); j++){
                String content = map.get(j);
                bodyList2.add(content);
            }
            bodyList.add(bodyList2);
        }
        return bodyList;
    }
}
