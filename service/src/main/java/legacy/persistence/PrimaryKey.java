package legacy.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     Title: legacy.persistence.PrimaryKey
 * </p>
 * is the primary key
 * </P>
 * @author hsimpsons
 * @creationDate May 17, 2008
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PrimaryKey {
	int index() default 0;
}
