package br.com.summa.zxed.act;

import javax.persistence.*;

import org.openxava.actions.*;

import br.com.summa.zxed.ex.*;
import br.com.summa.zxed.sql.*;

public class NativeDeleteAction extends ViewBaseAction implements IAction {

    @Override
    public void execute() throws Exception {
        try {
            NativeManager.delete(getModelName(), getView().getKeyValues(), getView().getValues());
            resetDescriptionsCache();
            addMessage("object_deleted", getModelName());
            getView().clear();
        } catch (PersistenceException ex) {
            throw new RootCauseException(ex);
        }
    }
}
