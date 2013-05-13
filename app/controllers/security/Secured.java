package controllers.security;

import model.operations.Admin;
import model.operations.DistrictOperations;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class Secured extends Security.Authenticator {

	@Override
    public String getUsername(Context ctx) {
        return ctx.session().get("login");
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return badRequest("you're not logged in");		
    }
    
    // Access rights
    
    public static boolean isAdmin() {
        return Admin.isAdmin(Context.current().request().username());
    }
    
    public static boolean isOwnerOf(Long district) {
        return DistrictOperations.isOwner(district, Context.current().request().username());
    }
    
	
}
