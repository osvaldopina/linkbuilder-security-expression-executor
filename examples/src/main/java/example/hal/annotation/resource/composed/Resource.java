package example.hal.annotation.resource.composed;

import com.github.osvaldopina.linkbuilder.annotation.SelfFromCurrentCall;
import com.github.osvaldopina.linkbuilder.hal.annotation.Variable;
import example.hal.annotation.resource.composed.link.Destination;
import example.hal.annotation.resource.composed.link.MyHalLink;
import example.hal.annotation.resource.composed.link.MyHalLinks;
import org.springframework.hateoas.ResourceSupport;

@SelfFromCurrentCall
@MyHalLinks({
        @MyHalLink(when = "hasRole('ROLE_USER')", destination = Destination.RESOURCE,overridedRel = "admin" ),
        @MyHalLink(when = "hasRole('ROLE_ADMIN')", destination = Destination.RESOURCE,overridedRel = "user")
})
public class Resource extends ResourceSupport {


}
