package core.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import core.math.Vec3f;
import core.model.Material;
import core.texturing.Texture2D;

public class ResourceLoader {

	public static Material loadMaterial(String path, String file) {
		long time = System.currentTimeMillis();
		
		BufferedReader mtlReader = null;
		Material material = null;
		
		if(file != null) {
			try {
				mtlReader = new BufferedReader(new FileReader("/res/" + path + file + ".mtl"));
				String line;
				
				while((line = mtlReader.readLine()) != null) {
					String[] tokens = line.split(" ");
					tokens = Util.removeEmptyStrings(tokens);
					
					if(tokens.length == 0) {
						continue;
					}
					if(tokens[0].equals("newmtl")) {
						material = new Material();
						material.setName(tokens[1]);
					}
					if(tokens[0].equals("Kd")) {
						if(tokens.length > 1) {
							Vec3f color = new Vec3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3]));
							material.setColor(color);
						}
					}
					if(tokens[0].equals("map_Kd")) {
						if(tokens.length > 1) {
							material.setDiffuseMap(new Texture2D("/res/" + path + "/" + tokens[1]));
						}
					}
					if(tokens[0].equals("map_Ks")) {
						if(tokens.length > 1) {
							material.setSpecularMap(new Texture2D("/res/" + path + "/" + tokens[1]));							
						}
					}
					if(tokens[0].equals("illum")) {
						if(tokens.length > 1) {
							material.setEmmission(Float.valueOf(tokens[1]));
						}
					}
					if(tokens[0].equals("Ns")) {
						if(tokens.length > 1) {
							material.setShininess(Float.valueOf(tokens[1]));
						}
					}
				}
				mtlReader.close();
				
			} catch (FileNotFoundException e) {
				System.err.println("ERROR: File not found: /res/" + path + file + ".mtl");
				e.printStackTrace();
				System.exit(1);
			} catch (IOException e) {
				System.err.println("ERROR: Failed to read file: /res/" + path + file + ".mlt");
				e.printStackTrace();
				System.exit(1);
			} 
		}
		System.out.println("INFO: Material loaded in: " + Long.toString(time - System.currentTimeMillis()));
		return material;
	}
}