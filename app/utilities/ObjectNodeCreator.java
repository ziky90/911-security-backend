package utilities;

import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;

public class ObjectNodeCreator {

	public static ObjectNode createHelpObjectNode(double lat, double lon){
		ObjectNode on = Json.newObject();
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("description", "");
		on.put("photo", "");
		on.put("flag", "help");
		return on;
	}
	
	public static ObjectNode createDangerObjectNode(double lat, double lon, String text){
		ObjectNode on = Json.newObject();
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("description", text);
		on.put("photo", "");
		on.put("flag", "danger");
		return on;
	}
	
	public static ObjectNode createDetailsObjectNode(double lat, double lon, String text){
		ObjectNode on = Json.newObject();
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("description", text);
		on.put("flag", "details");
		return on;
	}
	
	
	public static ObjectNode createPhotoObjectNode(double lat, double lon, String photo){
		ObjectNode on = Json.newObject();
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("description", "");
		on.put("photo", photo);
		on.put("flag", "photo");
		return on;
	}
	
}
