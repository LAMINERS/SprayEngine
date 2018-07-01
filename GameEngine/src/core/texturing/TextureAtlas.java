package core.texturing;

public class TextureAtlas extends Texture2D {

	private int numberOfRows = 1;
	
	public TextureAtlas(String file, int numberOfRows) {
		super(file);
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}	
}
