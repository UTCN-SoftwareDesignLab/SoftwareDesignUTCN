package org.example.l.good;

public class GoodGraphicsService {

    public void verify(Shape shape) {
        // totally different. can't set the dimensions of an abstract shape
        shape.getArea();
    }

}
