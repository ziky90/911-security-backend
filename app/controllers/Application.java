package controllers;

import javax.persistence.Query;

import model.Crime;
import model.operations.Admin;
import model.operations.DistrictOperations;

import org.codehaus.jackson.JsonNode;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
  
	public static Result index() {
    	//TODO return admin login site in here
        return ok("Got request " + request() + "!");
    }
	
	@Transactional(readOnly = true)
	@BodyParser.Of(BodyParser.Json.class)
	public static Result authenticateDistrict(){
		JsonNode json = request().body().asJson();
		
		String name = json.findPath("name").getTextValue();
		if(DistrictOperations.authenticate(name, json.findPath("password").getTextValue())){
			session("login", name);
			return ok("logged in");
		}
		
		return badRequest("wrong password");
	}
  
	@BodyParser.Of(BodyParser.Json.class)
	public static Result authenticateAdmin(){
		JsonNode json = request().body().asJson();
		
		if(Admin.authenticate(json.findPath("password").getTextValue())){
			session("login", "admin");
			return ok("logged in");
		}
		
		return badRequest("wrong password");		
	}
	
	
	@Transactional
	public static Result banUser(long id){
		Query query = JPA.em().createQuery("SELECT c FROM  Crime c WHERE c.id = :id");
		Crime c = (Crime) query.setParameter("id", id).getSingleResult();
		c.getClient().setAllowed(false);
		c.setActual(false);
		JPA.em().persist(c);
		return ok("banned");		
	}
	
	
	@Transactional
	public static Result archiveCrime(long id){
		Query query = JPA.em().createQuery("SELECT c FROM  Crime c WHERE c.id = :id");
		Crime c = (Crime) query.setParameter("id", id).getSingleResult();
		c.setActual(false);
		JPA.em().persist(c);
		return ok("archived");
	}
}
