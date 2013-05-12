package controllers;

import java.util.List;

import javax.persistence.Query;

import model.Crime;
import model.District;
import model.Info;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;


import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.F.Callback;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.WebSocket;
import websocket.WebSocketPool;
import controllers.security.Secured;

@Security.Authenticated(Secured.class)
// FIXME add this back after the jBcrypt problem solving
public class DistrictCommunication extends Controller {

	/**
	 * method for posting some information from the district point of view
	 * 
	 * @return
	 */
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result postInformation() {
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			if (Secured.isOwnerOf(json.findPath("district").asLong())) {
				Info info = new Info();
				District district = JPA.em().find(District.class,
						json.findPath("district").asLong());
				info.setDistrict(district);
				info.setTextInformation(json.findPath("information").asText());

				JPA.em().persist(info);

				return ok("" + info.getId());
			} else {
				return forbidden("you do not have right to do this");
			}
		}

	}

	/**
	 * method for updating the information
	 * 
	 * @return
	 */
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateInformation() {
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			Info info = JPA.em().find(Info.class, json.findPath("id").asLong());
			if (Secured.isOwnerOf(info.getDistrict().getId())) {
				info.setTextInformation(json.findPath("information").asText());
				JPA.em().persist(info);
				return ok("" + info.getId());
			} else {
				return forbidden("you do not have right to do this");
			}
		}
	}

	/**
	 * method for deleting of the information
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public static Result deleteInformation(long id) {

		Info info = JPA.em().find(Info.class, id);
		if (Secured.isOwnerOf(info.getDistrict().getId())) {
			JPA.em().remove(info);
			return ok("district deleted");
		} else {
			return forbidden("you do not have right to do this");
		}
	}

	/**
	 * method for listing of all the info for particular district
	 * 
	 * @param districtId
	 * @return
	 */
	@Transactional(readOnly = true)
	public static Result getAllInformations(long districtId) {

		if (Secured.isOwnerOf(districtId)) {
			Query query = JPA.em().createQuery(
					"SELECT d FROM  District d WHERE d.id = :districtId");
			District d = (District) query
					.setParameter("districtId", districtId).getSingleResult();
			List<Info> infos = d.getInfos();

			ObjectNode result = Json.newObject();
			ObjectNode informations = result.putObject("informations");
			for (Info info : infos) {
				ObjectNode information = informations.putObject("information");

				information.put("id", info.getId());
				information.put("text", info.getTextInformation());

			}

			return ok(result);
		}
		return forbidden("you do not have right to do this");
	}

	/**
	 * method for retrieving the info from the DB
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public static Result getInformation(long id) {

		Info info = JPA.em().find(Info.class, id);

		if (Secured.isOwnerOf(info.getDistrict().getId())) {
			ObjectNode result = Json.newObject();
			result.put("id", info.getId());
			result.put("text", info.getTextInformation());

			return ok(result);
		} else {
			return forbidden("you do not have right to do this");
		}
	}

	
	
	public static WebSocket<JsonNode> initWebSocket() {

		return new WebSocket<JsonNode>() {

			// Called when the Websocket Handshake is done.
			public void onReady(final WebSocket.In<JsonNode> in,
					final WebSocket.Out<JsonNode> out) {

				
				in.onMessage(new Callback<JsonNode>(){
					public void invoke(JsonNode event){
						
						if(event.get("status").asText().equals("login")){
							long id = event.get("id").asLong();
							String password = event.get("password").asText();
							WebSocketPool.connect(id, password, in, out);
						}else{
							
						}
												
					}
				});
			}

		};

	}
	
	
	@Transactional
	public static Result banUser(long id){
		Query query = JPA.em().createQuery("SELECT c FROM  Crime c WHERE c.id = :id");
		Crime c = (Crime) query.setParameter("id", id).getSingleResult();
		c.getClient().setAllowed(false);
		c.setActual(false);
		JPA.em().persist(c);
		return ok();		
	}
	
	
	@Transactional
	public static Result archiveCrime(long id){
		Query query = JPA.em().createQuery("SELECT c FROM  Crime c WHERE c.id = :id");
		Crime c = (Crime) query.setParameter("id", id).getSingleResult();
		c.setActual(false);
		JPA.em().persist(c);
		return ok();
	}
}
