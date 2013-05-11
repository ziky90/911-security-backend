package websocket;

import java.util.HashMap;
import java.util.Map;

import model.operations.DistrictOperations;

import org.codehaus.jackson.JsonNode;

import play.api.mvc.WebSocket;
import play.mvc.WebSocket.In;
import play.mvc.WebSocket.Out;

public class WebSocketPool {

	private static Map<Long, WebSocket<JsonNode>> activeSockets = new HashMap<Long, WebSocket<JsonNode>>();
	
	
	public static void connect(final long id,  In<JsonNode> in, Out<JsonNode> out){
		
		DistrictOperations.isOwner(id, "praha");
		
		
	}
}
