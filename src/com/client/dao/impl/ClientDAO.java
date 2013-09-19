package com.client.dao.impl;

import com.client.beans.domain.Client;
import com.client.beans.domain.ClientIp;
import com.client.beans.domain.ClientUl;
import com.client.criteria.ClientCriteria;
import com.client.criteria.ClientType;
import com.client.criteria.Criteria;
import com.client.dao.ProjectDAO;
import com.client.exceptions.ClientException;
import com.client.utils.ClientUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir Ugay
 * Date: 14.09.13
 * Time: 12:42
 */
@Repository
public class ClientDAO implements ProjectDAO<Client> {

    @Autowired
    private SessionFactory sessionFactory;

    //Клиентов с одинаковым статусом может быть несколько
    @Override
    public List<Client> find(Criteria criteria, long clientStatus) {
        Transaction tr = null;
        List<Client> list = new ArrayList<Client>();
        try {
            tr = sessionFactory.getCurrentSession().beginTransaction();
            list = sessionFactory.getCurrentSession().createCriteria(Client.class)
                    .add(Restrictions.eq("status", clientStatus))
                    .list();
            tr.commit();
        } catch (Exception e) {
            if (tr != null && tr.isActive()) tr.rollback();
            e.printStackTrace();
        }
        return list;
    }

    private ClientUtils clientUtils = ClientUtils.getInstance();

    @Override
    public Client create(Criteria criteria) {
        ClientCriteria clientCriteria = (ClientCriteria) criteria;
        Client client = new Client();
        client.setDate_created(new Date());

        if (clientCriteria.getType() == ClientType.IP) {
            ClientIp clientIp = new ClientIp();
            clientIp.setName(clientCriteria.getName());
            clientIp.setSurname(clientCriteria.getSurname());
            clientIp.setPassport_number(clientCriteria.getPassportNumber());
            clientIp.setClient(client);
            client.getClientIps().add(clientIp);

        } else if (clientCriteria.getType() == ClientType.UL) {
            ClientUl clientUl = new ClientUl();
            clientUl.setInn(clientCriteria.getInn());
            clientUl.setOrg_name(clientCriteria.getOrgName());
            clientUl.setClient(client);
            client.getClientUls().add(clientUl);

        } else {
            throw new ClientException("Не выбран походящий тип клиента.");
        }
        Transaction tr = null;
        try {
            tr = sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(client);
            tr.commit();
        } catch (Exception e) {
            if (tr != null && tr.isActive()) tr.rollback();
            e.printStackTrace();
        }

        client.setStatus(clientUtils.calculateStatus(client.getDate_created(), clientCriteria, client.getId()));

        return update(client);
    }

    @Override
    public Client delete(Client obj) {
        Transaction tr = null;
        try {
            tr = sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(obj);
            tr.commit();
        } catch (Exception e){
            if (tr != null && tr.isActive()) tr.rollback();
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Client update(Client obj) {
        Transaction tr = null;
        try {
            tr = sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().update(obj);
            tr.commit();
        } catch (Exception e){
            if (tr != null && tr.isActive()) tr.rollback();
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public List<Client> read(Criteria criteria) {
        Transaction tr = null;
        List<Client> list = new ArrayList<Client>();
        try {
            tr = sessionFactory.getCurrentSession().beginTransaction();
            list = sessionFactory.getCurrentSession().createCriteria(Client.class).list();
            tr.commit();
        } catch (Exception e){
            if (tr != null && tr.isActive()) tr.rollback();
            e.printStackTrace();
        }
        return list;
    }
}
