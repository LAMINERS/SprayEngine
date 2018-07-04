package modules.font;

import core.math.Vec2f;
import core.math.Vec3f;
import core.shader.Shader;
import core.utils.ResourceLoader;

public class FontShader extends Shader{

	public FontShader() {
		super();
		
		addVertexShader(ResourceLoader.loadShader("font.vert"));
		addFragmentShader(ResourceLoader.loadShader("font.frag"));
		
		addUniform("color");
		addUniform("translation");
		
		compileShader();
	}
	
	public void setColor(Vec3f color) {
			setUniform3f("color", color);
	}
	
	public void setTranslation(Vec2f translation) {
		setUniform2f("translation", translation);
	}
}
