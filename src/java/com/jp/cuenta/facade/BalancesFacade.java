/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.cuenta.facade;

import com.jp.cuenta.entity.Balances;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jp.cuenta.entity.Balances_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jp.cuenta.entity.Clientes;

/**
 *
 * @author Brian Gomez
 */
@Stateless
public class BalancesFacade extends AbstractFacade<Balances> {

    @PersistenceContext(unitName = "CuentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BalancesFacade() {
        super(Balances.class);
    }

    public boolean isIdClienteEmpty(Balances entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Balances> balances = cq.from(Balances.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(balances, entity), cb.isNotNull(balances.get(Balances_.idCliente)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Clientes findIdCliente(Balances entity) {
        return this.getMergedEntity(entity).getIdCliente();
    }
    
}
