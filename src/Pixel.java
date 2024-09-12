public class Pixel {
    private int x;
    private int y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Pixel pixel) {
        return this.x == pixel.getX() && this.y == pixel.getY();
    }

    public String toString() {
        return "Pixel at (" + x + ", " + y + ")";
    }
}
