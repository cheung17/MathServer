package core.exception;

import java.io.Writer;

import core.util.LogUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * freemarker异常拦截 
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
	@Override
	public void handleTemplateException(TemplateException templateException,
			Environment environment, Writer writer) throws TemplateException {
		/*LogUtil.error(this.getClass(), templateException.getMessage());
		try {
			this.sendEmail(templateException, environment, writer);// 发送错误邮件

			writer.write("<script>");
			writer.write("alert('ftl页面加载出现异常，请重试！')");
			writer.write("</script>");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(this.getClass(), e.getMessage());
		}*/
	}

	/**
	 * 发送错误邮件
	 * 
	 * @param templateException
	 * @param environment
	 * @param writer
	 * @throws Exception
	 */
	private void sendEmail(TemplateException templateException,
			Environment environment, Writer writer) throws Exception {
		//TODO 后续完善
	}

}
