package jsf.hockshop.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.hockshop.entities.User;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "hockshop-simplePU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create (User user) {
		em.persist(user);
	}
	
	public User merge (User user) {
		return em.merge(user);
	}
	
	public void remove (User user) {
		em.remove(em.merge(user));
	}
	
	public User find(Object id) {
		return em.find(User.class,id);
	}
	
	public List<User> getFullList(){
		List<User> list = null;
		
		Query query = em.createQuery("select u form User u");
		
		try {
			list = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<User> getList(Map<String, Object> searchParams){
		List<User> list =null;
		
		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby ="order by u.login asc, u.password";
		
		String login = (String) searchParams.get("login");
		if (login!=null) {
			if(where.isEmpty()) {
				where="where";
			}else {
				where+="and";
			}
			where +="u.login like :login ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);
		
		if(login != null) {
			query.setParameter("login", login+"%");
		}
		
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
