package utilities;

import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;

public class ObjectNodeCreator {

	public static ObjectNode createHelpObjectNode(long id, String uid, double lat, double lon){
		ObjectNode on = Json.newObject();
		on.put("id", id);
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("flag", "help");
		on.put("user", uid);
		return on;
	}
	
	public static ObjectNode createDangerObjectNode(long id, String uid, double lat, double lon, String text){
		ObjectNode on = Json.newObject();
		on.put("id", id);
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("description", text);
		on.put("flag", "danger");
		on.put("user", uid);
		return on;
	}
	
	public static ObjectNode createDetailsObjectNode(long id, String uid, double lat, double lon, String text){
		ObjectNode on = Json.newObject();
		on.put("id", id);
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("description", text);
		on.put("flag", "details");
		on.put("user", uid);
		return on;
	}
	
	
	public static ObjectNode createPhotoObjectNode(long id, String uid, double lat, double lon, String photo){
		ObjectNode on = Json.newObject();
		on.put("id", id);
		on.put("lat", lat);
		on.put("lon", lon);
		on.put("photo", photo);
		on.put("flag", "photo");
		on.put("user", uid);
		return on;
	}
	
}
