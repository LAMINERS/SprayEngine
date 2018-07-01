package core.math;

public class Transform {

	private Vec3f location;
	private Vec3f rotation;
	private Vec3f scaling;
	
	public Transform() {
		setLocation(new Vec3f());
		setRotation(new Vec3f());
		setScaling(new Vec3f());
	}
	
	public Mat4f getWorldMatrix() {
		Mat4f translationMatrix = new Mat4f().Translation(location);
		Mat4f rotationMatrix = new Mat4f().Rotation(rotation);
		Mat4f scalingMatrix = new Mat4f().Scaling(scaling);
		
		return translationMatrix.mul(scalingMatrix.mul(rotationMatrix));
	}
	
	public Mat4f getModelMatrix() {
		Mat4f rotationMatrix = new Mat4f().Rotation(rotation);
		
		return rotationMatrix;
	}
	
	public void setLocation(Vec3f location) {
		this.location = location;
	}

	public void setRotation(Vec3f rotation) {
		this.rotation = rotation;
	}

	public void setScaling(Vec3f scaling) {
		this.scaling = scaling;
	}

	public Vec3f getLocation() {
		return location;
	}

	public Vec3f getRotation() {
		return rotation;
	}

	public Vec3f getScaling() {
		return scaling;
	}
}
