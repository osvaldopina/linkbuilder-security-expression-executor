package example.annotation.controller.composed.link;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.github.osvaldopina.linkbuilder.annotation.Links;


@Links
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLinks {

    MyLink[] value() default {};

}
