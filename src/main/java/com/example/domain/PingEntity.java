package com.example.domain;

import java.util.Date;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="PingDocument")
public class PingEntity {

    @Id
    private ObjectId id;

    //@Indexed(unique = true)
    private String name;
    private Date timeEvent;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(Date timeEvent) {
        this.timeEvent = timeEvent;
    }

    public PingEntity() {
    }

    public PingEntity(String name) {

        this.name = name;
        this.timeEvent = new Date();
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final PingEntity other = (PingEntity) obj;

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("PingEntity{id=").append(id).append(", name=")
                .append(name).append(", timeEvent=")
                .append(timeEvent.getTime()).append("}");

        return builder.toString();
    }
   
}

