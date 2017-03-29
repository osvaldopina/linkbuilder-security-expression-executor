package example.annotation.resource.composed;

import example.annotation.resource.composed.link.Destionation;
import example.annotation.resource.composed.link.MyLinkDestination;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {

    @RequestMapping("/resource")
    @MyLinkDestination(destination = Destionation.RESOURCE)
    public void resource() {

    }

    @RequestMapping("/")
    public Resource root() {
        return new Resource();
    }


}

