package core.kernel;

public class Game {

	protected CoreEngine engine;
	
	public Game() {
		engine = new CoreEngine();
	}
	
	public void launch() {
		engine.start();
	}

	public CoreEngine getEngine() {
		return engine;
	}

	public void setEngine(CoreEngine engine) {
		this.engine = engine;
	}
}
