package modules.entities;

import core.math.Transform;
import core.math.Vec3f;
import core.scene.GameObject;

public class Light extends GameObject{
	
	private Vec3f color;
	private Vec3f attenuation = new Vec3f(1, 0, 0);
	
	public Light(Transform transform, Vec3f color) {
		super();
		this.setTransform(transform);
		this.setColor(color);
	}
	
	public Light(Transform transform, Vec3f color, Vec3f attenation) {
		super();
		this.setTransform(transform);
		this.setColor(color);
		this.setAttenuation(attenation);
	}

	public Vec3f getColor() {
		return color;
	}

	public void setColor(Vec3f color) {
		this.color = color;
	}

	public Vec3f getAttenuation() {
		return attenuation;
	}

	public void setAttenuation(Vec3f attenuation) {
		this.attenuation = attenuation;
	}
	
	
}
