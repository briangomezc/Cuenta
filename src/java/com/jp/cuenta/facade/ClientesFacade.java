/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.cuenta.facade;

import com.jp.cuenta.entity.Clientes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jp.cuenta.entity.Clientes_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jp.cuenta.entity.Balances;
import com.jp.cuenta.entity.Transacciones;
import java.util.Collection;

/**
 *
 * @author Brian Gomez
 */
@Stateless
public class ClientesFacade extends AbstractFacade<Clientes> {

    @PersistenceContext(unitName = "CuentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientesFacade() {
        super(Clientes.class);
    }

    public boolean isBalancesCollectionEmpty(Clientes entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Clientes> clientes = cq.from(Clientes.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(clientes, entity), cb.isNotEmpty(clientes.get(Clientes_.balancesCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Balances> findBalancesCollection(Clientes entity) {
        Clientes mergedEntity = this.getMergedEntity(entity);
        Collection<Balances> balancesCollection = mergedEntity.getBalancesCollection();
        balancesCollection.size();
        return balancesCollection;
    }

    public boolean isTransaccionesCollectionEmpty(Clientes entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Clientes> clientes = cq.from(Clientes.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(clientes, entity), cb.isNotEmpty(clientes.get(Clientes_.transaccionesCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Transacciones> findTransaccionesCollection(Clientes entity) {
        Clientes mergedEntity = this.getMergedEntity(entity);
        Collection<Transacciones> transaccionesCollection = mergedEntity.getTransaccionesCollection();
        transaccionesCollection.size();
        return transaccionesCollection;
    }
    
}
