package model.operations;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;

import model.District;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class DistrictOperations {

	@Transactional(readOnly = true)
	public static boolean isOwner(long district, String login){
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.id = :districtId");	
		District d = (District) query.setParameter("districtId", district).getSingleResult();
		return d.getName().equals(login);
	}
	
	@Transactional(readOnly = true)
	public static boolean authenticate(String login, String password){
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.name = :name");
		District d = (District) query.setParameter("name", login).getSingleResult();
		if(d == null){
			return false;
		}
		
		return BCrypt.checkpw(password, d.getPassword());		
		
	}
	
	
	public static boolean isOwnerTransact(long district, String password){
		EntityTransaction t = JPA.em().getTransaction();
		t.begin();
		Query query = JPA.em().createQuery("SELECT d FROM  District d WHERE d.id = :districtId");
		District d = (District) query.setParameter("districtId", district).getSingleResult();
		t.commit();
		if(d == null){
			return false;
		}
		return BCrypt.checkpw(password, d.getPassword());
	}
	
}
