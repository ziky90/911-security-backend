package websocket;

import java.util.HashMap;
import java.util.Map;

import model.operations.DistrictOperations;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.WebSocket.In;
import play.mvc.WebSocket.Out;


public class WebSocketPool {

	private static Map<Long, Out<JsonNode>> activeSockets = new HashMap<Long, Out<JsonNode>>();
	
	
	public static void connect(final long id,  In<JsonNode> in, Out<JsonNode> out){
		
		//DistrictOperations.isOwner(id, "praha");
		activeSockets.put(id, out);
		ObjectNode on = Json.newObject();
		on.put("message", "sucessfuly launched");
		out.write(on);
		
	}
	
	
	public static void writeToWebSocket(long id, ObjectNode data){
		Out<JsonNode> out = activeSockets.get(id);
		out.write(data);		
	}
}
