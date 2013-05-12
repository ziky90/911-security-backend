package model.operations;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Client;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class UserOperations {

	
	
	@Transactional
	public static Client getClient(String uid){
		Query query = JPA.em().createQuery("SELECT c FROM  Client c WHERE c.uid = :uid");
		Client c = null;
		try{
			c = (Client) query.setParameter("uid", uid).getSingleResult();
		}catch(NoResultException nre){
			c = new Client();
			c.setUid(uid);
			c.setAllowed(true);
			JPA.em().persist(c);
		}
		
		return c;		
		
	}
	
}
