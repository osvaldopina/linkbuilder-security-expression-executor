package example.builder;

import com.github.osvaldopina.linkbuilder.LinksBuilder;
import com.github.osvaldopina.linkbuilder.LinksBuilderFactory;
import com.github.osvaldopina.linkbuilder.annotation.LinkDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {

    @Autowired
    private LinksBuilderFactory linksBuilderFactory;

    @RequestMapping("/")
    public ResourceSupport root() {

        ResourceSupport resource = new ResourceSupport();

        LinksBuilder linksBuilder = linksBuilderFactory.create(resource);

        linksBuilder.link()
                .when("hasRole('ROLE_USER')")
                .withRel("user")
                .fromControllerCall(RootRestController.class)
                .resource();

        linksBuilder.link()
                .when("hasRole('ROLE_ADMIN')")
                .withRel("admin")
                .fromControllerCall(RootRestController.class)
                .resource();

        linksBuilder.buildAndSetAll();

        return resource;
    }


    @RequestMapping("/resource")
    @LinkDestination
    public ResourceSupport resource() {
        return null;
    }

 }

