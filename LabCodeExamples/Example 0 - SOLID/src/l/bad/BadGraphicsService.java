package l.bad;

public class BadGraphicsService {

    public BadSquare getNewRectangle() {
        // it can be an object returned by some factory ...
        return new BadSquare();
    }

    public void verify(BadRectangle r) {
        int width = 5;
        int height = 10;
        r.setWidth(width);
        r.setHeight(height);
        // user knows that r it's a rectangle.
        // It assumes that he's able to set the width and height as for the base class

        System.out.println(r.getArea());

        if (r.getArea() == width * height) {
            System.out.println("System runs smoothly 😎");
        } else {
            System.out.println("Critical error, crash.");
        }
    }

}
