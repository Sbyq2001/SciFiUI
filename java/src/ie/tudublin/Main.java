package ie.tudublin;

public class Main {
    public static void helloSciFi() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new SciFi());
    }

    public static void main(String[] args) {
        helloSciFi();
    }
}