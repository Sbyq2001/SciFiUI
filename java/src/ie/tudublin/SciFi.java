package ie.tudublin;

import processing.core.PApplet;

public class SciFi extends PApplet {
    int backgroundColor = color(10, 10, 50);
    int color1 = color(240, 22, 80);
    int color2 = color(242, 236, 46);
    int color3 = color(119, 235, 52);
    int color4 = color(59, 227, 224);
    int color5 = color(184, 82, 235);

    boolean sliderView = false;
    float sliderViewOffset = -300;

    float resolution;
    float radius = 25;
    float offset;

    // rad is for the rotation angle
    float rad = 0;

    float circleSize = 10;
    float numberOfcircles = 10;

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        background(0);
        colorMode(HSB);
    }

    public void draw() {
        fill(backgroundColor, 25);
        noStroke();
        rect(0, 0, width, height);
        noFill();

        stroke(color3, 150);
        strokeWeight(random(0, 1.5f));

        rect(10, 10, width - 20, height - 20, 5);
        translate(width / 2, height / 2);

        if (mouseX > 30 && mouseY > 30 && mouseX < 90 && mouseY < 90) {
            cursor(HAND);
            sliderView = true;
            sliderViewOffset = min(50, sliderViewOffset + 25);
        } else {
            cursor(ARROW);
            sliderView = false;
            sliderViewOffset = max(-300, sliderViewOffset - 25);
        }

        sliderCircle();
        sliderViewSultan();

        offset++;

        maincircle();
    }

    void sliderCircle() {
        noStroke();
        if (sliderView) {
            fill(color3, 120);
            ellipse(-(width / 2) + 60, -(height / 2) + 60, 70, 70);
            return;
        }

        fill(color3, 10);
        ellipse(-(width / 2) + 60, -(height / 2) + 60, 50, 50);
    }

    void sliderViewSultan() {
        stroke(color3, 150);
        fill(backgroundColor, 100);
        rect(-(width / 2) + sliderViewOffset, -(height / 2) + 120, 250, 120, 5);

        fill(color3);
        text("Sultan Alqahtani\nDate of birth 01/02/2001\nStudent @ TU Dublin", -(width / 2) + sliderViewOffset + 10,
                -(height / 2) + 140);
    }

    void maincircle() {
        noFill();
        stroke(color1, 150);
        strokeWeight(16);
        ellipse(0, 0, 250, 250);
        stroke(color3, 150);
        strokeWeight(1);
        ellipse(0, 0, 280, 280);
        for (int i = 0; i < 10; i++) {
            float offsect = i * PI / 5 + offset / 100.0F;
            stroke(backgroundColor);
            strokeWeight(1);
            line(sin(offsect) * 110, -cos(offsect) * 110, sin(offsect) * 135, -cos(offsect) * 135);

            stroke(color5);
            strokeWeight(2);
            line(sin(offsect) * 140, cos(offsect) * 140, sin(offsect) * 150, cos(offsect) * 150);
        }
    }
}