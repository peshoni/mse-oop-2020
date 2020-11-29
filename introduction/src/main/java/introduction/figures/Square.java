package introduction.figures;

public class Square extends Figure {
	private int width;

	public Square(int x, int y, int width) {

		this.width = width;
		this.x = x;
		this.y = y;

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int size) {
		this.width = size;
	}
}
