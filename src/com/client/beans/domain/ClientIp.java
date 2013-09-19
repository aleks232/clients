package com.client.beans.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir Ugay
 * Date: 13.09.13
 * Time: 12:51
 */
@Entity
@Table(name = "ClientIp")
public class ClientIp implements Serializable {
    private static final long serialVersionUID = -692193581226957552L;
    private String id;
    private String name;
    private String surname;
    private String passport_number;
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

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name="passport_number")
    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    @ManyToOne( cascade = CascadeType.ALL , targetEntity = Client.class)
    @JoinColumn(name = "client_id", nullable = false)
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

        ClientIp clientIp = (ClientIp) o;

        if (id != null ? !id.equals(clientIp.id) : clientIp.id != null) return false;
        if (name != null ? !name.equals(clientIp.name) : clientIp.name != null) return false;
        if (passport_number != null ? !passport_number.equals(clientIp.passport_number) : clientIp.passport_number != null)
            return false;
        if (surname != null ? !surname.equals(clientIp.surname) : clientIp.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (passport_number != null ? passport_number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientIp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", passport_number='" + passport_number + '\'' +
                '}';
    }
}
