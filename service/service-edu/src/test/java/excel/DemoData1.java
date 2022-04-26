package excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by 彭天怡 2022/4/22.
 */
@Data
public class DemoData1 {

    //表示sno对应的是excel表格中的第一列
    @ExcelProperty(index = 0)
    private Integer sno;

    @ExcelProperty(index = 1)
    private String sname;
}
