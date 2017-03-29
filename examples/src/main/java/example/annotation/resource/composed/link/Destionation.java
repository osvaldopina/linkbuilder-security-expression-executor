package example.annotation.resource.composed.link;

public enum Destionation {

    RESOURCE("resource");

    private String rel;

    Destionation(String rel) {
        this.rel = rel;
    }

    public String getRel() {
        return rel;
    }
}
