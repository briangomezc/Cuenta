package com.jp.cuenta.controller;

import com.jp.cuenta.entity.Transacciones;
import com.jp.cuenta.facade.TransaccionesFacade;
import com.jp.cuenta.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "transaccionesController")
@ViewScoped
public class TransaccionesController extends AbstractController<Transacciones> {

    @Inject
    private ClientesController idClienteController;
    @Inject
    private TipodocumentosController idTipodocumentoController;
    @Inject
    private MobilePageController mobilePageController;

    public TransaccionesController() {
        // Inform the Abstract parent controller of the concrete Transacciones Entity
        super(Transacciones.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idClienteController.setSelected(null);
        idTipodocumentoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Clientes controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCliente(ActionEvent event) {
        Transacciones selected = this.getSelected();
        if (selected != null && idClienteController.getSelected() == null) {
            idClienteController.setSelected(selected.getIdCliente());
        }
    }

    /**
     * Sets the "selected" attribute of the Tipodocumentos controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipodocumento(ActionEvent event) {
        Transacciones selected = this.getSelected();
        if (selected != null && idTipodocumentoController.getSelected() == null) {
            idTipodocumentoController.setSelected(selected.getIdTipodocumento());
        }
    }

}
