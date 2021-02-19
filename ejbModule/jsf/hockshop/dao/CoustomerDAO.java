package jsf.hockshop.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.hockshop.entities.Coustomer;

@Stateless
public class CoustomerDAO {
	private final static String UNIT_NAME = "hockshop-simplePU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create (Coustomer coustomer) {
		em.persist(coustomer);
	}
	
	public Coustomer merge (Coustomer coustomer) {
		return em.merge(coustomer);
	}
	
	public void remove (Coustomer coustomer) {
		em.remove(em.merge(coustomer));
	}
	
	public Coustomer find(Object id) {
		return em.find(Coustomer.class,id);
	}
	
	public List<Coustomer> getFullList(){
		List<Coustomer> list =null;
		
		Query query = em.createQuery("select c from Coustomer c");
		
		try {
			list = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public List<Coustomer> getList(Map<String,Object>searchParams){
		List<Coustomer> list=null;
		
		String select = "select c ";
		String from = "from Coustomer c ";
		String where ="";
		String orderby = "order by c.coustomerName, c.coustomerSurname";
		
		String coustomerName = (String) searchParams.get("coustomerName");
		if(coustomerName!=null) {
			if(where.isEmpty()) {
				where="where";
			}else {
				where+="and";
				
			}
			where+="c.coustomerName like :coustomerName";
		}
		
		Query query =em.createQuery(select+from+where+orderby);
		
		if(coustomerName!=null) {
			query.setParameter("coustomerName",coustomerName+"%");
		}
		
		try {
			list= query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
