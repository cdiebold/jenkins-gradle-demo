package testing2;

public class Pair {
	private int x;
	private int y;

	public Pair(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isEqual(Pair other) {
		if (this.getX() == other.getX() && this.getY() == other.getY())
			return true;
		else
			return false;

	}

	public boolean inverse(Pair other) {
		if (other.getX() == this.getY() && other.getY() == this.getX())
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
