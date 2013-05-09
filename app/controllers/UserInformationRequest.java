package controllers;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class UserInformationRequest extends Controller{

	@Transactional(readOnly=true)
	public static Result getInfoFeed(long lat, long lon, long oldest){
		return ok("current info feed");
	}
	
}
