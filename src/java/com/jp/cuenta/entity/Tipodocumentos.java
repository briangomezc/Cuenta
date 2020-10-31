/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.cuenta.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Brian Gomez
 */
@Entity
@Table(name = "TIPODOCUMENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodocumentos.findAll", query = "SELECT t FROM Tipodocumentos t")
    , @NamedQuery(name = "Tipodocumentos.findById", query = "SELECT t FROM Tipodocumentos t WHERE t.id = :id")
    , @NamedQuery(name = "Tipodocumentos.findByDescripcion", query = "SELECT t FROM Tipodocumentos t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Tipodocumentos.findByCuentaContable", query = "SELECT t FROM Tipodocumentos t WHERE t.cuentaContable = :cuentaContable")
    , @NamedQuery(name = "Tipodocumentos.findByEstado", query = "SELECT t FROM Tipodocumentos t WHERE t.estado = :estado")})
public class Tipodocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Short id;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;
    @Column(name = "ESTADO")
    private Boolean estado;
    @OneToMany(mappedBy = "idTipodocumento")
    private Collection<Transacciones> transaccionesCollection;

    public Tipodocumentos() {
    }

    public Tipodocumentos(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Transacciones> getTransaccionesCollection() {
        return transaccionesCollection;
    }

    public void setTransaccionesCollection(Collection<Transacciones> transaccionesCollection) {
        this.transaccionesCollection = transaccionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodocumentos)) {
            return false;
        }
        Tipodocumentos other = (Tipodocumentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jp.cuenta.entity.Tipodocumentos[ id=" + id + " ]";
    }
    
}
