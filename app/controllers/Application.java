package controllers;

import model.operations.Admin;
import model.operations.DistrictOperations;

import org.codehaus.jackson.JsonNode;

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
	
}
