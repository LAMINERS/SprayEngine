package core.kernel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;

import core.utils.Constants;

public class Window {

	private int width = 1280;
	private int height = 720;
	private boolean resizable = true;
	
	private static Window instance = null;
	
	public static Window getInstance() {
		if(instance == null) 
			instance = new Window();
		return instance;
	}
	
	public void create(int width, int height) {
		this.width = width;
		this.height = height;
		
		ContextAttribs attribs = new ContextAttribs(3, 3)
									 .withForwardCompatible(true)
									 .withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(getWidth(), getHeight()));
			Display.create(new PixelFormat().withSamples(8).withDepthBits(24), attribs);
			Display.setTitle("Game Engine");
			GL11.glEnable(GL13.GL_MULTISAMPLE);
			Display.setResizable(resizable);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Failed to create window");
			System.exit(1);
		}
		
		GL11.glViewport(0, 0, width, height);
	}
	
	public void update() {
		if(Display.wasResized()) {
			this.width = Display.getWidth();
			this.height = Display.getHeight();
			GL11.glViewport(0, 0, width, height);
		}
		Display.sync(Constants.FPS_CAP);
		Display.update();
	}
	
	public void close() {
		Display.destroy();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if(!this.resizable) {
			System.err.println("WARNING: Resizing is not allowed");
			return;
		}
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if(!this.resizable) {
			System.err.println("WARNING: Resizing is not allowed");
			return;
		}
		this.height = height;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
	
	
}
