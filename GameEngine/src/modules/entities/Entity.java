package modules.entities;

import core.math.Transform;
import core.math.Vec3f;
import core.model.Model;
import core.scene.GameObject;

public class Entity extends GameObject{

	private Model model;
	
	public Entity(Model model, Transform transform) {
		this.model = model;
		this.setTransform(transform);
	}
	
	public void increasePosition(Vec3f delta) {
		this.getTransform().setLocation(this.getTransform().getLocation().add(delta));
	}
	
	public void increaseRotation(Vec3f delta) {
		this.getTransform().setRotation(this.getTransform().getRotation().add(delta));
	}
	
	public void increaseScale(Vec3f delta) {
		this.getTransform().setScaling(this.getTransform().getScaling().add(delta));
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
