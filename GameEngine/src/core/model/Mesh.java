package core.model;

import core.utils.VaoLoader;

public class Mesh {

	private float[] vertices;
	private float[] textures;
	private float[] normals;
	private int[] indices;
	
	private int vaoID;
	
	public Mesh(float[] vertices, float[] textures, float[] normals, int[] indices) {
		this.vertices = vertices;
		this.textures = textures;
		this.normals = normals;
		this.indices = indices;
		this.vaoID = VaoLoader.loadToVAO(vertices, textures, normals, indices);
	}

	public float[] getVertices() {
		return vertices;
	}

	public float[] getTextures() {
		return textures;
	}

	public float[] getNormals() {
		return normals;
	}

	public int[] getIndices() {
		return indices;
	}
	
	public int getVertexCount() {
		return indices.length;
	}

	public int getVaoID() {
		return vaoID;
	}
}
