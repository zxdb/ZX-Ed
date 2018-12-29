package br.com.summa.zxed.act;

import org.openxava.actions.*;

import br.com.summa.zxed.sql.*;

public class NativeDeleteAction extends ViewBaseAction implements IAction {

    @Override
    public void execute() throws Exception {
        NativeManager.delete(getModelName(), getView().getKeyValues(), getView().getValues());
        resetDescriptionsCache();
        addMessage("object_deleted", getModelName());
        getView().clear();
    }
}
