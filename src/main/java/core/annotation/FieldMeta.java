package core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 注解字段备注
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Documented
public @interface FieldMeta {
    /**
     * 字段描述
     * @return
     */
    String columDesc();
}
