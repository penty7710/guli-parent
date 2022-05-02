package excel;

import com.alibaba.excel.EasyExcel;

/**
 * Created by 彭天怡 2022/4/22.
 */
public class TestEasyExcel1 {
    public static void main(String[] args) {

        //定义读取的文件的路径
        String filename = "F:\\write.xlsx";

        //第一个参数是文件路径名
        //第二个是实体类的类信息
        //第三个是监听器
        EasyExcel.read(filename,DemoData1.class,new ExcelListener()).sheet().doRead();

    }
}
