package example.hal.annotation.resource.composed;


import example.hal.annotation.resource.composed.link.Destination;
import example.hal.annotation.resource.composed.link.MyLinkDestination;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {

    @RequestMapping("/")
    public Resource root() {
        return new Resource();
    }

    @RequestMapping("/resource")
    @MyLinkDestination(destination = Destination.RESOURCE)
    public void resource() {
    }

}

