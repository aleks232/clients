package com.client.dao;

import com.client.criteria.ClientCriteria;
import com.client.criteria.Criteria;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir Ugay
 * Date: 14.09.13
 * Time: 12:37
 */
public interface ProjectDAO<T>{
    List<T> find(ClientCriteria clientCriteria);
    T create(Criteria criteria);
    T delete(T obj);
    T update(T obj);
    List<T> read(Criteria criteria);
}
