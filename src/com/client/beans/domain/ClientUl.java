package com.client.beans.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir Ugay
 * Date: 13.09.13
 * Time: 12:50
 */
@Entity
@Table(name = "ClientUl")
public class ClientUl implements Serializable {
    private static final long serialVersionUID = 7753736319708800113L;
    private String id;
    private String inn;
    private String org_name;
    private Client client;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="inn")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Column(name="org_name")
    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    @ManyToOne( cascade = CascadeType.ALL , targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(name="client_id", referencedColumnName = "id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientUl clientUl = (ClientUl) o;

        if (id != null ? !id.equals(clientUl.id) : clientUl.id != null) return false;
        if (inn != null ? !inn.equals(clientUl.inn) : clientUl.inn != null) return false;
        if (org_name != null ? !org_name.equals(clientUl.org_name) : clientUl.org_name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (inn != null ? inn.hashCode() : 0);
        result = 31 * result + (org_name != null ? org_name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientUl{" +
                "id='" + id + '\'' +
                ", inn='" + inn + '\'' +
                ", org_name='" + org_name + '\'' +
                '}';
    }
}
