package br.com.summa.zxed.act;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.validators.*;

import br.com.summa.zxed.ex.*;
import br.com.summa.zxed.sql.*;

public class NativeSaveNewAction extends SaveNewAction {

    @Override
    public void execute() throws Exception {
        try {
            Map<String, Object> key = getView().getKeyValues();
            if (getView().isKeyEditable()) {
                NativeManager.insert(getModelName(), key, getValuesToSave());
            } else {
                NativeManager.update(getModelName(), key, getValuesToSave());
            }
            returnsToPreviousViewUpdatingReferenceView(key);
        } catch (ValidationException ex) {
            addErrors(ex.getErrors());
        } catch (DuplicateKeyException ex) {
            addError("no_create_exists");
        } catch (PersistenceException ex) {
            throw new RootCauseException(ex);
        }
    }
}
