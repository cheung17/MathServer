package core.action;

import org.apache.log4j.Logger;

import core.util.ResultInfo;

public class BaseAct extends SuperAct {
    private static Logger log = Logger.getLogger(BaseAct.class);

    /**
     * 
     * @title resultInfoAccess
     * @description 统一处理异常
     * @param resultInfoAccess
     * @return
     */
    protected ResultInfo resultInfoAccess(ResultInfoAccess resultInfoAccess) {
	ResultInfo result = null;
	try {
	    Object result1 = resultInfoAccess.exec();
	    if (result1 instanceof ResultInfo) {
		result = (ResultInfo) result1;
	    } else {
		result = new ResultInfo();
		result.setSuccess("操作成功",
			result1 == null ? null : result1.toString());
	    }
	} catch (Exception e) {
	    result = new ResultInfo();
	    result.setFailure("操作失败：" + e.getMessage());
	    log.error(e);
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
	return result;
    }

    /**
     * 
     * @title access
     * @description 统一处理异常
     * @param access
     * @return
     */
    protected Object access(Access access) {
	Object result = null;
	try {
	    result = access.exec();
	} catch (Exception e) {
	    result = e.getMessage();
	    log.error(e);
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
	return result;
    }
}