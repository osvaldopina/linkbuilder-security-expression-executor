package example.hal.annotation.resource.composed.link;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.github.osvaldopina.linkbuilder.annotation.LinkDestination;

@LinkDestination
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLinkDestination {

    Destination destination();
}
