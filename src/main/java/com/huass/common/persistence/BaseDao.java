
package com.huass.common.persistence;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.huass.common.utils.Reflections;


public class BaseDao<T> {

	
	@Autowired
	private SessionFactory sessionFactory;

	
	private Class<?> entityClass;
	
	
	public BaseDao() {
		entityClass = Reflections.getClassGenricType(getClass());
	}

	
	public Session getSession(){  
	  return sessionFactory.getCurrentSession();
	}
	
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}

	
	public void flush(){
		getSession().flush();
	}

	
	public void clear(){ 
		getSession().clear();
	}
	
    
	public <E> List<E> find(String qlString){
		return find(qlString, null);
	}
    
    
	@SuppressWarnings("unchecked")
	public <E> List<E> find(String qlString, Parameter parameter){
		Query query = createQuery(qlString, parameter);
		return query.list();
	}
	
	public T findOne(String qlString, Parameter parameter)
	{
		Query query = createQuery(qlString, parameter);
		return (T) query.uniqueResult();
	}
	
	public String findCount(String qlString, Parameter parameter)
	{
		Query query = createQuery(qlString, parameter);
		return query.uniqueResult().toString();
	}
	
	
	public <E> List<E> find(String qlString, Parameter parameter, int startNum, int num)
	{
		Query query = createQuery(qlString, parameter);
		query.setFirstResult(startNum);
		query.setMaxResults(num);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return getSession().createCriteria(entityClass).list();
	}
	
	
	@SuppressWarnings("unchecked")
	public T get(Serializable id){
		return (T)getSession().get(entityClass, id);
	}
	
	
	public T getByHql(String qlString){
		return getByHql(qlString, null);
	}
	
	
	@SuppressWarnings("unchecked")
	public T getByHql(String qlString, Parameter parameter){
		Query query = createQuery(qlString, parameter);
		return (T)query.uniqueResult();
	}
	
	public void saveOrUpdate(T entity)
	{
		Object id = null;
		for (Method method : entity.getClass().getMethods())
		{
			Id idAnn = method.getAnnotation(Id.class);
			if (idAnn != null){
				try {
					id = method.invoke(entity);
					if(id != null && "".equals(id.toString()))
					{
						Reflections.setFieldValue(entity, "id", null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				break;
			}
		}
		getSession().saveOrUpdate(entity);
	}
	
	
	public void save(T entity)
	{
		getSession().save(entity);
	}
	
	
	public void save(List<T> entityList){
		for (T entity : entityList){
			save(entity);
		}
	}

	
	public int update(String qlString){
		return update(qlString, null);
	}
	
	public void update(T entity)
	{
		getSession().update(entity);
	}
	
	
	public int update(String qlString, Parameter parameter){
		return createQuery(qlString, parameter).executeUpdate();
	}
	
	
	
	public void delete(String id, Class cls)
	{
		T entity = (T) getSession().get(cls, id);
		this.delete(entity);
	}
	
	
	public void delete(T entity)
	{
		getSession().delete(entity);
	}
	
	
	
	
	public Query createQuery(String qlString, Parameter parameter){
		Query query = getSession().createQuery(qlString);
		setParameter(query, parameter);
		return query;
	}
    
   

	
	public <E> List<E> findBySql(String sqlString){
		return findBySql(sqlString, null, null);
	}
	
	
	public <E> List<E> findBySql(String sqlString, Parameter parameter){
		return findBySql(sqlString, parameter, null);
	}
	
	
	public <E> List<E> findBySql(String sqlString, Parameter parameter, int startNum, int num){
		SQLQuery query = createSqlQuery(sqlString, parameter);
		query.setFirstResult(startNum);
		query.setMaxResults(num);
		setResultTransformer(query, null);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public <E> List<E> findBySql(String sqlString, Parameter parameter, Class<?> resultClass){
		SQLQuery query = createSqlQuery(sqlString, parameter);
		setResultTransformer(query, resultClass);
		return query.list();
	}
	
	
	public int updateBySql(String sqlString, Parameter parameter){
		return createSqlQuery(sqlString, parameter).executeUpdate();
	}
	
	public int executeSQL(String sqlString, Parameter parameter){
		return createSqlQuery(sqlString, parameter).executeUpdate();
	}
	
	
	public int executeHQL(String hql, Parameter parameter)
	{
		Query query = getSession().createQuery(hql);
		setParameter(query, parameter);
		return query.executeUpdate();
	}
	
	
	public SQLQuery createSqlQuery(String sqlString, Parameter parameter){
		SQLQuery query = getSession().createSQLQuery(sqlString);
		setParameter(query, parameter);
		return query;
	}

	
	private void setResultTransformer(SQLQuery query, Class<?> resultClass){
		if (resultClass != null){
			if (resultClass == Map.class){
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			}else if (resultClass == List.class){
				query.setResultTransformer(Transformers.TO_LIST);
			}else{
				query.addEntity(resultClass);
			}
		}
	}
	
	
	private void setParameter(Query query, Parameter parameter){
		if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
                if(value instanceof Collection<?>){
                    query.setParameterList(string, (Collection<?>)value);
                }else if(value instanceof Object[]){
                    query.setParameterList(string, (Object[])value);
                }else{
                    query.setParameter(string, value);
                }
            }
        }
	}
	

	
	public List<T> find(DetachedCriteria detachedCriteria) {
		return find(detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> find(DetachedCriteria detachedCriteria, ResultTransformer resultTransformer) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.setResultTransformer(resultTransformer);
		return criteria.list(); 
	}
	
	
	@SuppressWarnings("rawtypes")
	public long count(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		long totalCount = 0;
		try {
			Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
			field.setAccessible(true);
			List orderEntrys = (List)field.get(criteria);
			field.set(criteria, new ArrayList());
			criteria.setProjection(Projections.rowCount());
			totalCount = Long.valueOf(criteria.uniqueResult().toString());
			criteria.setProjection(null);
			field.set(criteria, orderEntrys);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	
	public DetachedCriteria createDetachedCriteria(Criterion... criterions) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		for (Criterion c : criterions) {
			dc.add(c);
		}
		return dc;
	}
	public int getCount(String qlString){
		Query query = getSession().createQuery(qlString);
		int total = ((Long)query.uniqueResult()).intValue();   
		return total;
	}
	
	
	@SuppressWarnings("unchecked")
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria, ResultTransformer resultTransformer) {
		// get count
		if (!page.isDisabled() && !page.isNotCount()){
			page.setCount(count(detachedCriteria));
			if (page.getCount() < 1) {
				return page;
			}
		}
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.setResultTransformer(resultTransformer);
		// set page
		if (!page.isDisabled()){
	        criteria.setFirstResult(page.getFirstResult());
	        criteria.setMaxResults(page.getMaxResults()); 
		}
		// order by
		if (StringUtils.isNotBlank(page.getOrderBy())){
			for (String order : StringUtils.split(page.getOrderBy(), ",")){
				String[] o = StringUtils.split(order, " ");
				if (o.length==1){
					criteria.addOrder(Order.asc(o[0]));
				}else if (o.length==2){
					if ("DESC".equals(o[1].toUpperCase())){
						criteria.addOrder(Order.desc(o[0]));
					}else{
						criteria.addOrder(Order.asc(o[0]));
					}
				}
			}
		}
		page.setList(criteria.list());
		return page;
	}
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria) {
		return find(page, detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
	}
	
	public Page<T> find(Page<T> page) {
		return find(page, createDetachedCriteria());
	}
}
