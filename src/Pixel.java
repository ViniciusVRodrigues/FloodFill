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


    public boolean equals(Object object) {
        if (!(object instanceof Pixel)) {
            return false;
        }
        Pixel pixel = (Pixel) object;
        return x == pixel.x && y == pixel.y;
    }

    @Override
    public String toString() {
        return "Pixel at (" + x + ", " + y + ")";
    }
}
