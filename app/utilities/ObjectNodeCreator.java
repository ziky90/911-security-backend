package utilities;

import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;

public class ObjectNodeCreator {

	public static ObjectNode createHelpObjectNode(long id, double lat, double lon){
		ObjectNode on = Json.newObject();
		on.put("id", id);
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("flag", "help");
		return on;
	}
	
	public static ObjectNode createDangerObjectNode(long id, double lat, double lon, String text){
		ObjectNode on = Json.newObject();
		on.put("id", id);
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("description", text);
		on.put("flag", "danger");
		return on;
	}
	
	public static ObjectNode createDetailsObjectNode(long id, double lat, double lon, String text){
		ObjectNode on = Json.newObject();
		on.put("id", id);
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("description", text);
		on.put("flag", "details");
		return on;
	}
	
	
	public static ObjectNode createPhotoObjectNode(long id, double lat, double lon, String photo){
		ObjectNode on = Json.newObject();
		on.put("id", id);
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("photo", photo);
		on.put("flag", "photo");
		return on;
	}
	
}
