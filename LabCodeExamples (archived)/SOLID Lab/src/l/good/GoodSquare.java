package l.good;

public class GoodSquare extends Shape {

    private int dimension;

    public GoodSquare(int dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int width) {
        this.dimension = width;
    }

    @Override
    public int getArea() {
        return dimension * dimension;
    }
}
