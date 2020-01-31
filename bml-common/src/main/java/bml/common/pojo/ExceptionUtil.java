package bml.common.pojo;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
* @User 月影
* @Date 2020年1月30日
*/

public class ExceptionUtil {
	
	/**
	 * 获取异常的堆栈信息
	 */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            //将异常信息输出在控制台
            t.printStackTrace(pw);
            //将异常信息返回
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
