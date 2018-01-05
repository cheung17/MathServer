package core.util;

public class AssertUtil {

    public static final String DEFAULT_NOT_NULL = "更新失败：数据库没有更新";

    public static int assertNotZeroAndNum(int ret, int num, String excepStr) {
	if (ret == 0 || ret != num) {
	    throw new RuntimeException(excepStr);
	}
	return ret;
    }

    public static int assertNotZero(int ret, String excepStr) {
	if (ret == 0) {
	    throw new RuntimeException(excepStr);
	}
	return ret;
    }

    public static int assertNotZero(int ret) {
	return assertNotZero(ret, DEFAULT_NOT_NULL);
    }
}
