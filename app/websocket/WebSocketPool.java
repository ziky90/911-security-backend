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
	
	
	public static void connect(final long id, final String password,  In<JsonNode> in, Out<JsonNode> out){
		
		//if(DistrictOperations.isOwnerTransact(id, password)){
			activeSockets.put(id, out);
			ObjectNode on = Json.newObject();
			on.put("message", "Application successfuly launche and connected!!!");
			out.write(on);
		/*}else{
			ObjectNode on = Json.newObject();
			on.put("message", "your id or password is incorrect");
			out.write(on);
		}*/
		
	}
	
	
	public static void writeToWebSocket(long id, ObjectNode data){
		Out<JsonNode> out = activeSockets.get(id);
		out.write(data);		
	}
}
