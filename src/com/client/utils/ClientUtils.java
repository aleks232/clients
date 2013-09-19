package com.client.utils;

import com.client.criteria.ClientCriteria;
import com.client.criteria.ClientType;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir Ugay
 * Date: 15.09.13
 * Time: 15:33
 */
public class ClientUtils {
    private volatile static ClientUtils clientUtils;
    private Calendar calendar = Calendar.getInstance();

    private ClientUtils(){

    }

    public static ClientUtils getInstance(){
        if(clientUtils==null){
            clientUtils = new ClientUtils();
        }
        return clientUtils;
    }

    public Long getNumberFromStr(String s){
        if(s==null) return 0l;
        StringBuilder sb = new StringBuilder("0");
        for(char c:s.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
            }
        }
        return Long.valueOf(sb.toString());
    }

    public Long calculateStatus(Date dateCreated, ClientCriteria criteria, Long clientId){
        calendar.setTime(dateCreated);
        Long status = 0l;
        status +=calendar.get(Calendar.YEAR);
        System.out.println("status: "+status);
        status +=(criteria.getType()==ClientType.IP)?getNumberFromStr(criteria.getPassportNumber()):getNumberFromStr(criteria.getInn());
        System.out.println("status: "+status);
        if(clientId!=null){
            String sClientId = String.valueOf(clientId);
            if(sClientId.length()>1){
                status += Long.valueOf(sClientId.substring(0, 1));
            } else {
                status += clientId;
            }
        }
        return status;
    }
}
