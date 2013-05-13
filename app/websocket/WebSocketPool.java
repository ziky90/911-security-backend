package websocket;

import java.util.HashMap;
import java.util.Map;

import model.operations.DistrictOperations;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.db.jpa.JPA;
import play.libs.F.Function0;
import play.libs.Json;
import play.mvc.WebSocket.In;
import play.mvc.WebSocket.Out;

public class WebSocketPool {

	private static Map<Long, Out<JsonNode>> activeSockets = new HashMap<Long, Out<JsonNode>>();
	
	
	public static void connect(final long id, final String password,  In<JsonNode> in, Out<JsonNode> out){		
		
		
		boolean result = false;
		try {
			result = JPA.withTransaction(new Function0<Boolean>(){
			    public Boolean apply() throws Throwable{
			        return DistrictOperations.isOwnerSecure(id, password);
			    }
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		if(result){
			activeSockets.put(id, out);
			ObjectNode on = Json.newObject();
			on.put("message", "Application successfuly launched and connected!!!");
			out.write(on);
		}else{
			ObjectNode on = Json.newObject();
			on.put("message", "your id or password is incorrect");
			out.write(on);
			out.close();
		}
		
	}
	
	
	public static void ban(final long id, Out<JsonNode> out){
		
		boolean result = false;
		try {
			result = JPA.withTransaction(new Function0<Boolean>(){
			    public Boolean apply() throws Throwable{
			        return DistrictOperations.banClient(id);
			    }
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		if(result){
			ObjectNode on = Json.newObject();
			on.put("message", "User banned successfuly");
			out.write(on);
		}else{
			ObjectNode on = Json.newObject();
			on.put("message", "User bann problem");
			out.write(on);
		}
	}
	
	public static void solve(final long id, Out<JsonNode> out){
		
		boolean result = false;
		try {
			result = JPA.withTransaction(new Function0<Boolean>(){
			    public Boolean apply() throws Throwable{
			        return DistrictOperations.archiveCrime(id);
			    }
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		if(result){
			ObjectNode on = Json.newObject();
			on.put("message", "Crime successfuly archived");
			out.write(on);
		}else{
			ObjectNode on = Json.newObject();
			on.put("message", "Archivation problem");
			out.write(on);
		}
	}
	
	
	public static void writeToWebSocket(long id, ObjectNode data){
		Out<JsonNode> out = activeSockets.get(id);
		out.write(data);		
	}
}
