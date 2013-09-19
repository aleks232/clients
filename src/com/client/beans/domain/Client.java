package com.client.beans.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir Ugay
 * Date: 13.09.13
 * Time: 12:49
 */
@Entity
@Table(name = "Client")
public class Client implements Serializable {
    private static final long serialVersionUID = -8113478042040767554L;
    private Long id;
    private Date date_created;
    private Long status;
    private Set<ClientIp> clientIps = new HashSet<ClientIp>();
    private Set<ClientUl> clientUls = new HashSet<ClientUl>();

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Type(type = "date")
    @Column(name="date_created")
    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @Column(name="status")
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @OneToMany(mappedBy="client", targetEntity = ClientIp.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<ClientIp> getClientIps() {
        return clientIps;
    }

    @OneToMany(mappedBy="client", targetEntity = ClientUl.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<ClientUl> getClientUls() {
        return clientUls;
    }

    public void setClientIps(Set<ClientIp> clientIps) {
        this.clientIps = clientIps;
    }

    public void setClientUls(Set<ClientUl> clientUls) {
        this.clientUls = clientUls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (date_created != null ? !date_created.equals(client.date_created) : client.date_created != null)
            return false;
        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (status != null ? !status.equals(client.status) : client.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date_created != null ? date_created.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", date_created=" + date_created +
                ", status=" + status +
                ", clientIps=" + clientIps +
                ", clientUls=" + clientUls +
                '}';
    }
}
