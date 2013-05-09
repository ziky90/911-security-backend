package controllers;

import java.util.List;

import javax.persistence.Query;

import model.District;
import model.Info;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class DistrictCommunication extends Controller{

	/**
	 * method for posting some information from the district point of view
	 * @return
	 */
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result postInformation(){
		JsonNode json = request().body().asJson();
		if(json == null){
			return badRequest("expecting json data");
		}else{
			Info info = new Info();
			District district = JPA.em().find(District.class, json.findPath("district").asLong());
			info.setDistrict(district);
			info.setTextInformation(json.findPath("information").asText());
			
			JPA.em().persist(info);
			
			return ok(""+info.getId());
		}
		
	}
	
	/**
	 * method for updating the information
	 * @return
	 */
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateInformation(){
		JsonNode json = request().body().asJson();
		if(json == null){
			return badRequest("expecting json data");
		}else{
			Info info = JPA.em().find(Info.class, json.findPath("id").asLong());
			info.setTextInformation(json.findPath("information").asText());
			
			JPA.em().persist(info);
			
			return ok(""+info.getId());
		}
	}
	
	/**
	 * method for deleting of the information
	 * @param id
	 * @return
	 */
	//TODO security is very important here
	@Transactional
	public static Result deleteInformation(long id){
		
		Info info = JPA.em().find(Info.class, id);
		JPA.em().remove(info);
			
		return ok("district deleted");
	}
	
	/**
	 * method for listing of all the info for particular district
	 * @param districtId
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result getAllInformations(long districtId){
		
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.id = ?districtId", District.class);
		District d = (District) query.setParameter("districtId", districtId).getSingleResult();
		List<Info> infos = d.getInfos();
		

		ObjectNode result = Json.newObject();
		ObjectNode informations = result.putObject("informations");
		for(Info info : infos){
			ObjectNode information = informations.putObject("information");
			
			information.put("id", info.getId());
			information.put("text", info.getTextInformation());
			
		}
		
		return ok(result);
	}
	
	/**
	 * method for retrieving the info from the DB
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result getInformation(long id){
		
		Info info = JPA.em().find(Info.class, id);
		
		ObjectNode result = Json.newObject();
		result.put("id", info.getId());
		result.put("text", info.getTextInformation());
				
		return ok(result);
	}
}
