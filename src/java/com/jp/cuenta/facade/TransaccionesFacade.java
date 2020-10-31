/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.cuenta.facade;

import com.jp.cuenta.entity.Transacciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jp.cuenta.entity.Transacciones_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jp.cuenta.entity.Clientes;
import com.jp.cuenta.entity.Tipodocumentos;

/**
 *
 * @author Brian Gomez
 */
@Stateless
public class TransaccionesFacade extends AbstractFacade<Transacciones> {

    @PersistenceContext(unitName = "CuentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransaccionesFacade() {
        super(Transacciones.class);
    }

    public boolean isIdClienteEmpty(Transacciones entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Transacciones> transacciones = cq.from(Transacciones.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(transacciones, entity), cb.isNotNull(transacciones.get(Transacciones_.idCliente)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Clientes findIdCliente(Transacciones entity) {
        return this.getMergedEntity(entity).getIdCliente();
    }

    public boolean isIdTipodocumentoEmpty(Transacciones entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Transacciones> transacciones = cq.from(Transacciones.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(transacciones, entity), cb.isNotNull(transacciones.get(Transacciones_.idTipodocumento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tipodocumentos findIdTipodocumento(Transacciones entity) {
        return this.getMergedEntity(entity).getIdTipodocumento();
    }
    
}
