package com.jp.cuenta.controller;

import com.jp.cuenta.entity.Clientes;
import com.jp.cuenta.entity.Balances;
import com.jp.cuenta.entity.Transacciones;
import java.util.Collection;
import com.jp.cuenta.facade.ClientesFacade;
import com.jp.cuenta.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "clientesController")
@ViewScoped
public class ClientesController extends AbstractController<Clientes> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isBalancesCollectionEmpty;
    private boolean isTransaccionesCollectionEmpty;

    public ClientesController() {
        // Inform the Abstract parent controller of the concrete Clientes Entity
        super(Clientes.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsBalancesCollectionEmpty();
        this.setIsTransaccionesCollectionEmpty();
    }

    public boolean getIsBalancesCollectionEmpty() {
        return this.isBalancesCollectionEmpty;
    }

    private void setIsBalancesCollectionEmpty() {
        Clientes selected = this.getSelected();
        if (selected != null) {
            ClientesFacade ejbFacade = (ClientesFacade) this.getFacade();
            this.isBalancesCollectionEmpty = ejbFacade.isBalancesCollectionEmpty(selected);
        } else {
            this.isBalancesCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Balances entities that
     * are retrieved from Clientes and returns the navigation outcome.
     *
     * @return navigation outcome for Balances page
     */
    public String navigateBalancesCollection() {
        Clientes selected = this.getSelected();
        if (selected != null) {
            ClientesFacade ejbFacade = (ClientesFacade) this.getFacade();
            Collection<Balances> selectedBalancesCollection = ejbFacade.findBalancesCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Balances_items", selectedBalancesCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/balances/index";
    }

    public boolean getIsTransaccionesCollectionEmpty() {
        return this.isTransaccionesCollectionEmpty;
    }

    private void setIsTransaccionesCollectionEmpty() {
        Clientes selected = this.getSelected();
        if (selected != null) {
            ClientesFacade ejbFacade = (ClientesFacade) this.getFacade();
            this.isTransaccionesCollectionEmpty = ejbFacade.isTransaccionesCollectionEmpty(selected);
        } else {
            this.isTransaccionesCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Transacciones entities
     * that are retrieved from Clientes and returns the navigation outcome.
     *
     * @return navigation outcome for Transacciones page
     */
    public String navigateTransaccionesCollection() {
        Clientes selected = this.getSelected();
        if (selected != null) {
            ClientesFacade ejbFacade = (ClientesFacade) this.getFacade();
            Collection<Transacciones> selectedTransaccionesCollection = ejbFacade.findTransaccionesCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Transacciones_items", selectedTransaccionesCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/transacciones/index";
    }

}
