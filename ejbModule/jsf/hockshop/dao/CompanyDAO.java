package jsf.hockshop.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.hockshop.entities.Company;


@Stateless
public class CompanyDAO {
	private final static String UNIT_NAME = "hockshop-simplePU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create (Company company) {
		em.persist(company);
	}
	
	public Company merge (Company company) {
		return em.merge(company);
	}
	
	public void remove (Company company) {
		em.remove(em.merge(company));
	}
	
	public Company find(Object id) {
		return em.find(Company.class,id);
	}
	
	public List<Company> getFullList(){
		List<Company> list = null;
		
		Query query = em.createQuery("select c form Company c");
		
		try {
			list = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Company> getList(Map<String, Object> searchParams){
		List<Company> list =null;
		
		String select = "select c ";
		String from = "from Company c ";
		String where = "";
		String orderby ="order by c.name asc, c.city";
		
		String name = (String) searchParams.get("name");
		if (name!=null) {
			if(where.isEmpty()) {
				where="where ";
			}else {
				where+="and ";
			}
			where +="c.name like :name ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);
		
		if(name != null) {
			query.setParameter("name", name+"%");
		}
		
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

