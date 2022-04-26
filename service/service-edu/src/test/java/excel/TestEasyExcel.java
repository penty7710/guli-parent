package excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭天怡 2022/4/22.
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作

        //1.设置写入文件地址和名称
        String filename = "F:\\write.xlsx";

        //2.调用easyexcal
        //write方法中两个参数：第一个参数文件路径名称，第二个参数文件实体类class
        //sheet是excel表格底下的分类
        //dowrite需要传入一个列表
        EasyExcel.write(filename,DemoData.class).sheet("学生列表1").doWrite(getData());
    }


    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return  list;
    }
}
