package l.bad;

public class BadSquare extends BadRectangle {

    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public String toString() {
        return "I am a square";
    }
}
