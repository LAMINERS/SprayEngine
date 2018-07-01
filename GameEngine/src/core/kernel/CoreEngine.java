package core.kernel;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;


public class CoreEngine {

	private static long lastFrameTime;
	private static float delta;
	private boolean isRunning;
	
	public void createWindow(int width, int height) {
		Window.getInstance().create(width, height);
		getDeviceProperties();
		lastFrameTime = getCurrentTime();
	}
	
	public void start() {
		if(isRunning) 
			return;
		run();
	}
	
	private void run() {
		this.isRunning = true;
		createWindow(1280, 720);

		while(isRunning) {
			
			if(Window.getInstance().isCloseRequested()) 
				stop();
			
			update();
			long currentFrameTime = getCurrentTime();
			delta = (currentFrameTime - lastFrameTime) / 1000f;
			lastFrameTime = currentFrameTime;
		}
	}
	
	private void update() {
		Window.getInstance().update();
		//TODO Camera Update;
	}
	
	private void stop() {
		if(!isRunning) 
			return;
		isRunning = false;
	}
	
	public static float getDeltaSecounds() {
		return delta;
	}
	

	public static long getFrameTime() {
		return lastFrameTime;
	}
	
	private static long getCurrentTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	private void getDeviceProperties() {
		System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION) + " bytes");
		System.out.println("Max Geometry Uniform Blocks: " + GL31.GL_MAX_GEOMETRY_UNIFORM_BLOCKS+ " bytes");
		System.out.println("Max Geometry Shader Invocations: " + GL40.GL_MAX_GEOMETRY_SHADER_INVOCATIONS + " bytes");
		System.out.println("Max Uniform Buffer Bindings: " + GL31.GL_MAX_UNIFORM_BUFFER_BINDINGS + " bytes");
		System.out.println("Max Uniform Block Size: " + GL31.GL_MAX_UNIFORM_BLOCK_SIZE + " bytes");
		System.out.println("Max SSBO Block Size: " + GL43.GL_MAX_SHADER_STORAGE_BLOCK_SIZE + " bytes");	
	}
}
