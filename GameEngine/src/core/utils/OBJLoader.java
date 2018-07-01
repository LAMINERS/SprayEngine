package core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.math.Vec2f;
import core.math.Vec3f;
import core.model.Mesh;

public class OBJLoader {

	private static final String MODEL_LOC = "/res/models/";
	
	public static Mesh loadOBJ(String file) {
		FileReader fr = null;
		
		try {
			fr = new FileReader(new File(MODEL_LOC + file + ".obj"));
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Failed to load file: " + MODEL_LOC + file + ".obj");
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fr);
		String line;
		List<Vec3f> vertices = new ArrayList<Vec3f>();
		List<Vec2f> textures = new ArrayList<Vec2f>();
		List<Vec3f> normals = new ArrayList<Vec3f>();
		List<Integer> indices = new ArrayList<Integer>();
		
		float[] verticesArray = null;
		float[] normalsArray = null;
		float[] textureArray = null;
		int[] indicesArray = null;
		
		try {
			while(true) {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				if(tokens[0].equals("v ")) {
					Vec3f vertex = new Vec3f(Float.valueOf(tokens[1]), 
											 Float.valueOf(tokens[2]), 
											 Float.valueOf(tokens[3]));
					vertices.add(vertex);
				} else if(tokens[0].equals("vt ")) {
					Vec2f texture = new Vec2f(Float.valueOf(tokens[1]), 
											  Float.valueOf(tokens[2]));
					textures.add(texture);
				} else if(tokens[0].equals("vn ")) {
					Vec3f normal = new Vec3f(Float.valueOf(tokens[1]),
											 Float.valueOf(tokens[2]),
											 Float.valueOf(tokens[3]));
					normals.add(normal);
				} else if(tokens[0].equals("f ")) {
					textureArray = new float[vertices.size() * 2];
					normalsArray = new float[vertices.size() * 3];
					break;
				}
			}
		
			while(line != null) {
				if(!line.startsWith("f ")) {
					line = reader.readLine();
					continue;
				}
				String[] tokens = line.split(" ");
				String[] vertex1 = tokens[1].split("/");
				String[] vertex2 = tokens[2].split("/");
				String[] vertex3 = tokens[3].split("/");
				
				processVertex(vertex1, indices, textures, normals, textureArray, normalsArray);
				processVertex(vertex2, indices, textures, normals, textureArray, normalsArray);
				processVertex(vertex3, indices, textures, normals, textureArray, normalsArray);
				line = reader.readLine();
			}
			reader.close();
			
		} catch (IOException e) {
			System.err.println("ERROR: Failed to read File: " + file + ".obj");
			e.printStackTrace();
		}	
		
		verticesArray = new float[vertices.size() * 3];
		indicesArray = new int[indices.size()];
		
		int vertexPointer = 0;
		for(Vec3f vertex: vertices) {
			verticesArray[vertexPointer++] = vertex.getX();
			verticesArray[vertexPointer++] = vertex.getY();
			verticesArray[vertexPointer++] = vertex.getZ();
		}
		
		for(int i = 0; i < indices.size(); i++) {
			indicesArray[i] = indices.get(i);
		}
		
		return new Mesh(verticesArray, textureArray, normalsArray, indicesArray);
	}
	
	private static void processVertex(String[] vertexData, List<Integer> indices, List<Vec2f> textures, List<Vec3f> normals, float[] textureArray, float[] normalsArray) {
		int currentVertexPoint = Integer.parseInt(vertexData[0]) - 1;
		indices.add(currentVertexPoint);
		Vec2f currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
		textureArray[currentVertexPoint * 2] = currentTex.getX();
		textureArray[currentVertexPoint * 2 + 1] = 1 - currentTex.getY();
		Vec3f currentNormal = normals.get(Integer.parseInt(vertexData[2]) - 1);
		normalsArray[currentVertexPoint * 3 + 0] = currentNormal.getX();
		normalsArray[currentVertexPoint * 3 + 1] = currentNormal.getY();
		normalsArray[currentVertexPoint * 3 + 2] = currentNormal.getZ();
	}
}
