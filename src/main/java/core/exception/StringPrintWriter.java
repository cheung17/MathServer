package core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常堆栈信息获取类
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class StringPrintWriter extends PrintWriter {
	public StringPrintWriter() {
		super(new StringWriter());
	}

	public StringPrintWriter(int initialSize) {
		super(new StringWriter(initialSize));
	}

	public String getString() {
		flush();
		return ((StringWriter) this.out).toString();
	}

	@Override
	public String toString() {
		return getString();
	}

	/**
	 * 获取异常的堆栈字符串
	 * @param e
	 * @return
	 */
	public static String getExceptionString(Exception e) {
		StringPrintWriter spw = new StringPrintWriter();
		e.printStackTrace(spw);
		return spw.getString();
	}

}
