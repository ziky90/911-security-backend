package model.operations;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Client;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class UserOperations {

	@Transactional(readOnly = true)
	public static boolean isAlloved(String login){
		Query query = JPA.em().createQuery("SELECT c FROM  Client c WHERE c.uid = :uid");	
		Client c = (Client) query.setParameter("uid", login).getSingleResult();
		return c.getAllowed();
	}
	
	
	@Transactional
	public static boolean authenticate(String login){
		Query query = JPA.em().createQuery("SELECT c FROM  Client c WHERE c.uid = :uid");
		Client c = null;
		try{
			c = (Client) query.setParameter("uid", login).getSingleResult();
		}catch(NoResultException nre){
			c = new Client();
			c.setUid(login);
			c.setAllowed(true);
			JPA.em().persist(c);
		}
		if(c == null){
			return false;
		}
		
		return c.getAllowed();		
		
	}
	
}
