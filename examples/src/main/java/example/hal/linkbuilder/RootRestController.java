package example.hal.linkbuilder;

import com.github.osvaldopina.linkbuilder.LinksBuilder;
import com.github.osvaldopina.linkbuilder.LinksBuilderFactory;
import com.github.osvaldopina.linkbuilder.annotation.SelfFromCurrentCall;
import com.github.osvaldopina.linkbuilder.hal.HalLinkBuilder;
import example.hal.annotation.controller.composed.link.Destination;
import example.hal.annotation.controller.composed.link.MyLinkDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {

    @Autowired
    private LinksBuilderFactory linksBuilderFactory;

    @RequestMapping("/")
    @SelfFromCurrentCall
    public ResourceSupport root() {

        ResourceSupport resource = new ResourceSupport();

        LinksBuilder linksBuilder = linksBuilderFactory.create(resource);

        linksBuilder.link()
                .when("hasRole('ROLE_ADMIN')")
                .withRel("admin")
                .extendTo(HalLinkBuilder.class)
                .fromControllerCall(RootRestController.class)
                .resource();

        linksBuilder.link()
                .when("hasRole('ROLE_USER')")
                .withRel("user")
                .extendTo(HalLinkBuilder.class)
                .fromControllerCall(RootRestController.class)
                .resource();

        linksBuilder.buildAndSetAll();

        return resource;
    }

    @RequestMapping("/resource")
    @MyLinkDestination(destination = Destination.RESOURCE)
    public void resource() {

    }


}

