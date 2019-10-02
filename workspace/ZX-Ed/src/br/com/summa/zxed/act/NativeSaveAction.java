package br.com.summa.zxed.act;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.model.*;

import br.com.summa.zxed.ex.*;
import br.com.summa.zxed.sql.*;

public class NativeSaveAction extends SaveAction {

    @Override
    public void execute() throws Exception {
        try {
        	super.execute();
        } catch (PersistenceException ex) {
            throw new RootCauseException(ex);
        }
    }

    @Override
    protected Map<String, Object> modify() throws Exception {
        Map<String, Object> keyValues = getView().getKeyValues();
        try {
            NativeManager.update(getModelName(), keyValues, getValuesToSave());
            addMessage("entity_modified", getModelName());
        } catch (NothingToSaveException ex) {
            addMessage("zxed_nothing_to_save");
        }
        getView().clear();
        return MapFacade.getValues(getModelName(), keyValues, getView().getMembersNamesWithHidden());
    }

    @Override
    protected Map<String, Object> create() throws Exception {
        Map<String, Object> keyValues = getView().getKeyValues();
        NativeManager.insert(getModelName(), keyValues, getValuesToSave());
        addMessage("entity_created", getModelName());
        getView().clear();
        return MapFacade.getValues(getModelName(), keyValues, getView().getMembersNamesWithHidden());
    }
}
