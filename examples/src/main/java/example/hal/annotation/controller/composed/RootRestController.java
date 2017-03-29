package example.hal.annotation.controller.composed;

import com.github.osvaldopina.linkbuilder.LinksBuilderFactory;
import example.hal.annotation.controller.composed.link.Destination;
import example.hal.annotation.controller.composed.link.MyHalLink;
import example.hal.annotation.controller.composed.link.MyHalLinks;
import example.hal.annotation.controller.composed.link.MyLinkDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {

	@Autowired
	private LinksBuilderFactory linksBuilderFactory;

	@RequestMapping("/")
	@MyHalLinks({
			@MyHalLink(when = "hasRole('ROLE_ADMIN')", destination = Destination.RESOURCE, overridedRel = "admin"),
			@MyHalLink(when = "hasRole('ROLE_USER')", destination = Destination.RESOURCE, overridedRel = "user")
	})
	public ResourceSupport root() {
		return new ResourceSupport();
	}

	@RequestMapping("/resource")
	@MyLinkDestination(destination = Destination.RESOURCE)
	public void directLink() {

	}


}

