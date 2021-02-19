package jsf.hockshop.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.hockshop.entities.Photo;

@Stateless
public class PhotoDAO {
	private final static String UNIT_NAME = "hockshop-simplePU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Photo photo) {
		em.persist(photo);
	}

	public Photo merge (Photo photo) {
		return em.merge(photo);
	}
	
	public void remove (Photo photo) {
		em.remove(em.merge(photo));
	}
	
	public Photo find(Object id) {
		return em.find(Photo.class,id);
	}
	
	public List<Photo> getFullList(){
		List<Photo> list = null;
		
		Query query = em.createQuery("select p from Photo p");
		
		try {
			list = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
	}
	return list;
	}
	
	public List<Photo>getList(Map<String,Object> searchParams){
		List<Photo> list= null;
		
		String select = "select p ";
		String from = " from Photo p  ";
		String where = "";
		String orderby = " order by p.url, p.photoDescription";
		
		String url = (String) searchParams.get("url");
		if (url!=null) {
			if(where.isEmpty()) {
				where="where";
			}else {
				where+="and";
			}
			where +="p.url like :url ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);
		
		if(url != null) {
			query.setParameter("url", url+"%");
		}
		
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
}
