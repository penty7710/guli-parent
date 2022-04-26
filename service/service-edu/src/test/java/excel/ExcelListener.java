package excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * 监听器
 */
public class ExcelListener extends AnalysisEventListener<DemoData1> {

    //一行一行的读取excel的内容
    @Override
    public void invoke(DemoData1 demoData1, AnalysisContext analysisContext) {
        System.out.println("内容"+demoData1);
    }

    //读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }

    //读取完成之后执行的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
