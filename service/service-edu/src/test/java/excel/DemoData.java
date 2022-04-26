package excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by 彭天怡 2022/4/22.
 */
@Data
public class DemoData {

    //设置exccel表头名称

    @ExcelProperty("学生编号")
    private Integer sno;

    @ExcelProperty("学生姓名")
    private String sname;


}
