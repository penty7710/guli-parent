import cn.hutool.Hutool;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @author : pety
 * @date : 2022/5/4 20:08
 */
public class test {

    @Test
    public void f(){
        DateTime yesterday = DateUtil.yesterday();
        String format = DateUtil.format(yesterday, "yyyy-MM-dd HH:mm:ss");
        System.out.println(format);


    }
}
