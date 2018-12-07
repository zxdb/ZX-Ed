package br.com.summa.zxed.act;

import java.util.*;

import javax.ejb.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.validators.*;

import br.com.summa.zxed.sql.*;

@lombok.Setter
public class NativeSaveAction extends SaveAction {

    public void execute() throws Exception {
        try {
            if (getView().isKeyEditable()) {
                updateView2(create2(), isResetAfterOnCreate());
            } else {
                updateView2(modify2(), isResetAfterOnModify());
            }
            resetDescriptionsCache();
        } catch (ValidationException ex) {
            addErrors(ex.getErrors());
        } catch (ObjectNotFoundException ex) {
            addError("no_modify_no_exists");
        } catch (DuplicateKeyException ex) {
            addError("no_create_exists");
        }
    }

    private void updateView2(Map<String, Object> values, boolean resetAfter) {
        if (resetAfter) {
            getView().setKeyEditable(true);
            commit();
            getView().reset();
        } else {
            getView().setKeyEditable(false);
            if (isRefreshAfter()) {
                getView().setValues(values);
            }
        }
    }

    private Map<String, Object> modify2() throws Exception {
        Map<String, Object> keyValues = getView().getKeyValues();
        NativeManager.update(getModelName(), keyValues, getValuesToSave());
        addMessage("entity_modified", getModelName());
        if (!isResetAfterOnModify() && isRefreshAfter()) {
            getView().clear();
            return MapFacade.getValues(getModelName(), keyValues, getView().getMembersNamesWithHidden());
        }
        return null;
    }

    private Map<String, Object> create2() throws Exception {
        NativeManager.insert(getModelName(), getView().getKeyValues(), getValuesToSave());
        addMessage("entity_created", getModelName());
        getTab().reset();
        return null;
    }
}
