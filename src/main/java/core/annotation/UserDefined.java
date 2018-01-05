/**
 * 
 */
package core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解，用于标注自定义的字段
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UserDefined {

	/**
	 * 转换来源
	 * @return
	 */
	public String transition_source() default "";

	/**
	 * 转换函数 example:"函数名"
	 * @return
	 */
	public String transition_function() default "";

	/**
	 * 通过固定列表进行转换 example:"0,'停用',1,'启用'"
	 * @return
	 */
	public String transition_list() default "";

	/**
	 * 查询表的别名
	 * @return
	 */
	public String table_alias() default "";
	
	/**
	 * 数据库列名
	 * @return
	 */
	public String db_fieldName() default "";
}
