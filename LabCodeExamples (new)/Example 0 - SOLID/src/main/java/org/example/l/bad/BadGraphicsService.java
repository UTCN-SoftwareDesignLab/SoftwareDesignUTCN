package org.example.l.bad;

public class BadGraphicsService {

  public BadRectangle getNewRectangle() {
    // it can be an object returned by some factory ...
    return new BadSquare();
  }

  public void verify(BadRectangle r) {
    int width = 5;
    int height = 10;
    r.setWidth(width);
    r.setHeight(height);
    // user knows that r is a rectangle.
    // It assumes that he's able to set the width and height as for the base class

    int area = r.getArea();
    System.out.println(area);

    if (area == width * height) {
      System.out.println("System runs smoothly 😎");
    } else {
      System.out.println("Critical error, crash.");
    }
  }

}
