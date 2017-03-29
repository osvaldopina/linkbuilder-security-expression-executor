package example.annotation.controller.composed.link;

public enum Destination {

    RESOURCE("resource");

     private String rel;

    Destination(String rel) {
        this.rel = rel;
    }

    public String getRel() {
        return rel;
    }
}
