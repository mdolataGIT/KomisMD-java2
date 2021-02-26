package jsf.hockshop.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.hockshop.entities.Coustomer;
import jsf.hockshop.entities.Specelem;

@Stateless
public class SpecelemDAO {
	private final static String UNIT_NAME = "hockshop-simplePU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create (Specelem specelem) {
		em.persist(specelem);
	}
	
	public Specelem merge (Specelem specelem) {
		return em.merge(specelem);
	}
	
	public void remove (Specelem specelem) {
		em.remove(em.merge(specelem));
	}
	
	public Specelem find(Object id) {
		return em.find(Specelem.class,id);
	}
	
	public List<Specelem> getFullList(){
		List<Specelem> list =null;
		
		Query query = em.createQuery("select c from Specelem c");
		
		try {
			list = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public List<Specelem> getList(Map<String,Object>searchParams){
		List<Specelem> list=null;
		
		String select = "select c ";
		String from = "from Specelem c ";
		String where ="";
		String orderby = "order by c.specName, c.specValue";
		
		String specName = (String) searchParams.get("specName");
		
		if(specName!=null) {
			if(where.isEmpty()) {
				where="where";
			}else {
				where+="and";
				
			}
			where+="c.specName like :specName";
		}
		
		Integer carId = (Integer) searchParams.get("carId");
		if(carId!=null) {
			if(where.isEmpty()) {
				where="where ";
			}else {
				where+="and ";
			}
			where+=" c.car.idCar = :idCa ";
		}
		
		Query query =em.createQuery(select+from+where+orderby);
		
		if(specName!=null) {
			query.setParameter("coustomerName",specName+"%");
		}
		
		if(carId!=null) {
			query.setParameter("idCa",carId);
		}
		
		try {
			list= query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
