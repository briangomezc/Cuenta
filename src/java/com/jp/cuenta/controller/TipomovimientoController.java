package com.jp.cuenta.controller;

import com.jp.cuenta.entity.Tipomovimiento;
import com.jp.cuenta.facade.TipomovimientoFacade;
import com.jp.cuenta.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "tipomovimientoController")
@ViewScoped
public class TipomovimientoController extends AbstractController<Tipomovimiento> {

    @Inject
    private MobilePageController mobilePageController;

    public TipomovimientoController() {
        // Inform the Abstract parent controller of the concrete Tipomovimiento Entity
        super(Tipomovimiento.class);
    }

}
