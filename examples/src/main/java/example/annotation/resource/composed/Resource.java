package example.annotation.resource.composed;

import com.github.osvaldopina.linkbuilder.annotation.Variable;
import example.annotation.resource.composed.link.Destionation;
import example.annotation.resource.composed.link.MyLink;
import example.annotation.resource.composed.link.MyLinks;
import org.springframework.hateoas.ResourceSupport;

@MyLinks(value = {
		@MyLink(when = "hasRole('ROLE_ADMIN')", destination = Destionation.RESOURCE, overridedRel = "admin"),
		@MyLink(when = "hasRole('ROLE_USER')", destination = Destionation.RESOURCE, overridedRel = "user")
})
public class Resource extends ResourceSupport {
}
