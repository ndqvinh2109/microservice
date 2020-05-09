package com.nab.assignment.shopping.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class ProductAudit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "audit_action", length = 20)
    private String auditAction;

    @Column(name = "audit_reference", length = 20)
    private String auditReference;

    @Temporal(TemporalType.TIMESTAMP)
    private Date auditActionTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getAuditActionTime() {
        return auditActionTime;
    }

    public void setAuditActionTime(Date auditActionTime) {
        this.auditActionTime = auditActionTime;
    }

    public String getAuditAction() {
        return auditAction;
    }

    public void setAuditAction(String auditAction) {
        this.auditAction = auditAction;
    }

    public String getAuditReference() {
        return auditReference;
    }

    public void setAuditReference(String auditReference) {
        this.auditReference = auditReference;
    }

}
