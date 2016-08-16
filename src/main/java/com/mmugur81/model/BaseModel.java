package com.mmugur81.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mugurel on 16.08.2016.
 * BaseModel base model for created and updated time
 */

@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    /******************************************************************************************************************/

    @PrePersist
    protected void onCreate() {
        this.created = this.updated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
    }

    /******************************************************************************************************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
