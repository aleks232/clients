package com.client.controller;

import com.client.beans.domain.Client;
import com.client.beans.model.User;
import com.client.criteria.ClientCriteria;
import com.client.dao.ProjectDAO;
import com.client.exceptions.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir Ugay
 * Date: 13.09.13
 * Time: 18:01
 */
@Controller
public class ClientController {
    private static Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ProjectDAO<Client> dao;

    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("dd.MM.yyyy").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            public String getAsText() {
                if (getValue() == null || getValue() == "") return "";
                return new SimpleDateFormat("dd.MM.yyyy").format((Date) getValue());
            }

        });
    }

    @ModelAttribute("user")
    public User getLoginForm() {
        return new User();
    }

    @ModelAttribute("criteria")
    public ClientCriteria getCriteria() {
        return new ClientCriteria();
    }


    @RequestMapping("/listClients")
    public String getClients(Map model) {
        List<Client> list = dao.read(new ClientCriteria());
        logger.debug("list: {}", list);
        model.put("clients", list);
        return "clients";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("criteria") @Valid ClientCriteria criteria,
                         BindingResult result, Map model) {
        logger.debug("criteria: {}", criteria);
        logger.debug("result.getAllErrors(): {}", result.getAllErrors());
        if (result.hasErrors()) {
            return "error";
        }
        try {
            Client client = dao.create(criteria);
            logger.debug("client: {}", client);
        } catch (ClientException ce) {
            logger.error("---- ClientException ----");
            logger.error("Error create client: ", ce);
            model.put("message", ce.getMessage());
            return "error";
        }
//        model.put("criteria", criteria);
        return "redirect:/forms/listClients";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findClients(@ModelAttribute("client") ClientCriteria criteria, Map model) {
        logger.debug("criteria.getClientId(): {}", criteria.getClientId());
        logger.debug("criteria.getDateCreated(): {}", criteria.getDateCreated());
        List<Client> list = dao.find(criteria);
        model.put("clients", list);
        return "clients";
    }

    @RequestMapping(value = "/clientInfo", method = RequestMethod.POST)
    public String clientInfo(@ModelAttribute("client") ClientCriteria criteria, Map model) {
        model.put("clients", criteria);
        return "clients";
    }
}
