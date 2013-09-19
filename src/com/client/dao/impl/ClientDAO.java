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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private static final Logger logger = LoggerFactory.getLogger(ClientDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Client> find(ClientCriteria clientCriteria) {
        List<Client> list = new ArrayList<Client>();
        try {
            org.hibernate.Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
            //Поиск по id
            if (clientCriteria.getClientId() != null) {
                Criterion restId = Restrictions.eq("id", clientCriteria.getClientId());
                criteria.add(Restrictions.and(restId));
            }
            //Поиск по дате
            if (clientCriteria.getDateStart() != null && clientCriteria.getDateEnd()!=null) {
                Criterion restDate = Restrictions.between("date_created", clientCriteria.getDateStart(),clientCriteria.getDateEnd());
                criteria.add(Restrictions.and(restDate));
            }

            list = criteria.list();
        } catch (Exception e) {
            logger.error("---- Exception ----");
            logger.error("Error in find client: ",e);
        }
        return list;
    }

    private ClientUtils clientUtils = ClientUtils.getInstance();

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
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
        try {
            sessionFactory.getCurrentSession().save(client);
        } catch (Exception e) {
            logger.error("---- Exception ----");
            logger.error("Error in create client: ",e);
        }

        client.setStatus(clientUtils.calculateStatus(client.getDate_created(), clientCriteria, client.getId()));

        return update(client);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Client delete(Client obj) {
        try{
            sessionFactory.getCurrentSession().delete(obj);
        } catch (Exception e){
            logger.error("---- Exception ----");
            logger.error("Error in delete client: ",e);
        }
        return obj;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Client update(Client obj) {
        try{
            sessionFactory.getCurrentSession().update(obj);
        } catch (Exception e){
            logger.error("---- Exception ----");
            logger.error("Error in update client: ",e);
       }
        return obj;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Client> read(Criteria criteria) {
        return sessionFactory.getCurrentSession().createCriteria(Client.class).list();
    }
}
