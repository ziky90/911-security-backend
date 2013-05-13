package model.operations;

import javax.persistence.Query;

import model.District;

import org.mindrot.jbcrypt.BCrypt;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class DistrictOperations {

	
	public static boolean isOwner(long district, String login){
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.id = :districtId");	
		District d = (District) query.setParameter("districtId", district).getSingleResult();
		return d.getName().equals(login);
	}
	
	
	public static boolean authenticate(String login, String password){
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.name = :name");
		District d = (District) query.setParameter("name", login).getSingleResult();
		if(d == null){
			return false;
		}
		
		return BCrypt.checkpw(password, d.getPassword());		
		
	}
	
	
	public static boolean isOwnerSecure(long district, String password){
		
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.id = :districtId");
		District d = (District) query.setParameter("districtId", district).getSingleResult();
		
		if(d == null){
			return false;
		}
		return BCrypt.checkpw(password, d.getPassword());
	}
	
}
