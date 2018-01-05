package core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCustomUtil {
	 private static ApplicationContext  ctx = new ClassPathXmlApplicationContext("/conf/application-context.xml");
	 /**
	  * 获取读jdbcTemplateRead
	  * @return
	  */
	 public static Object getWriteJdbctemplate(){
		 return ctx.getBean("jdbcTemplateWrite");
	 }
	 /**
	  * 获取写jdbcTemplateRead
	  * @return
	  */
	 public static Object getReadJdbcTemplate(){
		 return ctx.getBean("jdbcTemplateRead");
	 }
	 
}
