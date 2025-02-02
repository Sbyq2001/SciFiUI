package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

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

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        background(0);
        colorMode(HSB);
        minim = new Minim(this);
        ap = minim.loadFile("AvengersSoundtrack.mp3", 1024);
        ap.play();
        ab = ap.mix;
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
        rainbowCircles();

        radar();
        radarBar(-250, 600, 3, color1);
        radarBar(-190, 600, 1, color2);
        radarBar(-130, 600, 2, color3);
        radarBar(-70, 600, 4, color4);
        radarBar(-10, 600, 0.5f, color5);

        layerCircle();
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

    void rainbowCircles() {
        for (int i = 0; i < numberOfcircles; i++) {
            float newRad = rad + map(i, 0, numberOfcircles, 0, 2 * PI);
            float radius = i * circleSize + circleSize;
            fill(map(rad, 0, 2 * PI, 0, 255), 255, 255);
            ellipse(cos(newRad) * radius, sin(newRad) * radius, circleSize, circleSize);
        }
        rad += PI / 10;
        if (rad > 2 * PI)
            rad = 0;
    }

    void radar() {
        noStroke();
        fill(0, 5);

        stroke(color3);
        strokeWeight(20);
        float angle = radians(millis() / 22.5f);
        line(
                (float) (width / 3 - 20),
                (float) (height / 3 - 20),
                (float) (width / 3 - 20 + sin(angle) * (((height + width) / 6) / 2.25)),
                (float) (height / 3 - 20 - cos(angle) * (((height + width) / 6) / 2.25)));
    }

    void radarBar(float x, float y, float speed, int color) {
        float positionForX = x - width / 2 + 300;
        float positionForY = y - height / 2 + 40;
        float offsetforY = map(sin((float) (offset * 0.01 * speed)), -1, 1, 10, 125);

        strokeWeight(1);
        stroke(120, 220, 200, 120);
        noFill();
        rect(positionForX, positionForY, 30, 125);

        noStroke();

        fill(color);
        rect(positionForX, 125 + positionForY, 30, -(125 - offsetforY));
        rect(positionForX - 10, positionForY + offsetforY, 50, 5);
    }

    void layerCircle() {
        noStroke();

        pushMatrix();
        shearX((float) (PI / 4.0));
        translate(600, -300);
        for (int i = 0; i < 20; i++) {

            fill(color1, 5 + i * 5);
            ellipse(-i * 15 - cos((float) (offset / 50.0)) * 5 * (5 - i),
                    i * 15 + cos((float) (offset / 50.0)) * 5 * (5 - i), 80f, 80f);
        }
        popMatrix();
    }
}