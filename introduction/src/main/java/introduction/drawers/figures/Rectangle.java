package introduction.drawers.figures;

public class Rectangle extends Square {
	private int height;

	public Rectangle(int x, int y, int width, int height) {
		super(x, y, width);
		this.height = height;

	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
