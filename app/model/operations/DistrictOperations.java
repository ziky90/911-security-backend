package model.operations;

import javax.persistence.Query;

import model.District;
import play.db.jpa.JPA;

public class DistrictOperations {

	public static boolean isOwner(long district, String login){
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.id = ?districtId", District.class);
		District d = (District) query.setParameter("districtId", district).getSingleResult();
		return d.getName().equals(login);
	}
	
	public static boolean authenticate(String login, String password){
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.name = ?name", District.class);
		District d = (District) query.setParameter("name", login).getSingleResult();
		if(d == null){
			return false;
		}
		return d.getPassword().equals(password);		
		
	}
	
}
