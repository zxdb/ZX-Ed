package br.com.summa.zxed.act;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.validators.*;

import br.com.summa.zxed.ex.*;
import br.com.summa.zxed.sql.*;

public class NativeUpdateAction extends UpdateAction {

    @Override
    public void execute() throws Exception {
        try {
            Map<String, Object> key = getView().getKeyValues();
            NativeManager.update(getModelName(), key, getValuesToSave());
            returnsToPreviousViewUpdatingReferenceView(key);
        } catch (ValidationException ex) {
            addErrors(ex.getErrors());
        } catch (ObjectNotFoundException ex) {
            addError("no_modify_no_exists");
        } catch (PersistenceException ex) {
            throw new RootCauseException(ex);
        }
    }
}
