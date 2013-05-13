package model.operations;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Crime;
import model.District;

import org.codehaus.jackson.node.ObjectNode;
import org.mindrot.jbcrypt.BCrypt;

import play.db.jpa.JPA;
import play.libs.Json;

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
	
	
	public static boolean banClient(long id){
		Query query = JPA.em().createQuery("SELECT c FROM  Crime c WHERE c.id = :id");
		Crime c = (Crime) query.setParameter("id", id).getSingleResult();
		c.getClient().setAllowed(false);
		c.setActual(false);
		JPA.em().persist(c);
		return true;
	}
	
	
	public static boolean archiveCrime(long id){
		Query query = JPA.em().createQuery("SELECT c FROM  Crime c WHERE c.id = :id");
		try{
			Crime c = (Crime) query.setParameter("id", id).getSingleResult();
			c.setActual(false);
			JPA.em().persist(c);
		}catch(NoResultException e){
			return false;
		}
		
		return true;
	}
	
}
