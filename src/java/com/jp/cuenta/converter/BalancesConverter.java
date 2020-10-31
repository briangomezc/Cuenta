package com.jp.cuenta.converter;

import com.jp.cuenta.entity.Balances;
import com.jp.cuenta.facade.BalancesFacade;
import com.jp.cuenta.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "balancesConverter")
public class BalancesConverter implements Converter {

    private BalancesFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.getEjbFacade().find(getKey(value));
    }

    java.lang.Short getKey(String value) {
        java.lang.Short key;
        key = Short.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Short value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Balances) {
            Balances o = (Balances) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Balances.class.getName()});
            return null;
        }
    }

    private BalancesFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(BalancesFacade.class).get();
        return this.ejbFacade;
    }
}
