package core.kernel;

import core.math.Vec3f;

public class Camera {
	
	private static Camera instance = null;

	private Vec3f position;
	private Vec3f rotation;
	private float FOV = 70f;
	private float near = 0.01f;
	private float far = 10000f;
	
	public static Camera getInstance() {
		if(instance == null)
			instance = new Camera();
		return instance;
	}
	
	public void update() {
		checkInputs();
		move();
	}
	
	private void move() {
		
	}
	
	private void checkInputs() {
		
	}
	
	public void invertPitch() {
		this.rotation.setX(-rotation.getX());
	}

	public Vec3f getPosition() {
		return position;
	}

	public void setPosition(Vec3f position) {
		this.position = position;
	}

	public Vec3f getRotation() {
		return rotation;
	}

	public void setRotation(Vec3f rotation) {
		this.rotation = rotation;
	}

	public float getFOV() {
		return FOV;
	}

	public void setFOV(float fOV) {
		FOV = fOV;
	}

	public float getNear() {
		return near;
	}

	public void setNear(float near) {
		this.near = near;
	}

	public float getFar() {
		return far;
	}

	public void setFar(float far) {
		this.far = far;
	}
}
