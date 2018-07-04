package modules.font;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modules.font.mesh.FontType;
import modules.font.mesh.GUIText;
import modules.font.mesh.TextMeshData;

public class TextMaster {

	private static Map<FontType, List <GUIText>> texts = new HashMap<FontType, List<GUIText>>();
	private static FontRenderer renderer;
	
	public static void init() {
		renderer = new FontRenderer();
	}
	
	public static void render() {
		renderer.render(texts);
	}
	
	public static void loadText(GUIText text) {
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = loader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<GUIText> textBatch = texts.get(font);
		if(textBatch == null) {
			textBatch = new ArrayList<GUIText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
}
