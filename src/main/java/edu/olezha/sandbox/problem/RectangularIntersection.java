package edu.olezha.sandbox.problem;

public class RectangularIntersection {

    public static void main(String[] args) {
        Rectangle first = new Rectangle(1, 1, 6, 3);
        Rectangle second = new Rectangle(5, 2, 3, 6);
        System.out.println(rectangularIntersection(first, second)); // 5, 2, 2, 2

        first = new Rectangle(-1, -1, 6, 3);
        second = new Rectangle(-5, -2, 5, 6);
        System.out.println(rectangularIntersection(first, second)); // -1, -1, 1, 3

        first = new Rectangle(1, 1, 6, 3);
        second = new Rectangle(5, 2, 3, 1);
        System.out.println(rectangularIntersection(first, second)); // 5, 2, 2, 1 TODO
    }

    static Rectangle rectangularIntersection(Rectangle first, Rectangle second) {
        int xNormalization = Math.min(first.leftX, second.leftX);
        if (xNormalization < 0) {
            first.leftX -= xNormalization;
            second.leftX -= xNormalization;
        } else xNormalization = 0;

        int yNormalization = Math.min(first.bottomY, second.bottomY);
        if (yNormalization < 0) {
            first.bottomY -= yNormalization;
            second.bottomY -= yNormalization;
        } else yNormalization = 0;

        Rectangle rectangle = new Rectangle();

        if (first.leftX <= second.leftX && first.leftX + first.width > second.leftX) {
            rectangle.leftX = second.leftX;
            rectangle.width = first.leftX + first.width - second.leftX;
        } else  if (second.leftX <= first.leftX && second.leftX + second.width > first.leftX) {
            rectangle.leftX = first.leftX;
            rectangle.width = second.leftX + second.width - first.leftX;
        } else {
            return rectangle;
        }

        if (first.bottomY <= second.bottomY && first.bottomY + first.height > second.bottomY) {
            rectangle.bottomY = second.bottomY;
            rectangle.height = first.bottomY + first.height - second.bottomY;
        } else if (second.bottomY <= first.bottomY && second.bottomY + second.height > first.bottomY) {
            rectangle.bottomY = first.bottomY;
            rectangle.height = second.bottomY + second.height - first.bottomY
                    - (second.height - first.bottomY - first.height);
        } else {
            rectangle.leftX = 0;
            rectangle.width = 0;
            return rectangle;
        }

        if (xNormalization < 0) rectangle.leftX += xNormalization;
        if (yNormalization < 0) rectangle.bottomY += yNormalization;

        return rectangle;
    }
}

class Rectangle {

    // coordinates of bottom left corner
    int leftX;
    int bottomY;

    // dimensions
    int width;
    int height;

    public Rectangle() {}

    public Rectangle(int leftX, int bottomY, int width, int height) {
        this.leftX = leftX;
        this.bottomY = bottomY;
        this.width  = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "leftX=" + leftX +
                ", bottomY=" + bottomY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}