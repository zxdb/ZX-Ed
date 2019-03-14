package br.com.summa.zxed.act;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.model.*;

import br.com.summa.zxed.ex.*;
import br.com.summa.zxed.sql.*;

public class NativeDeleteSelectedAction extends TabBaseAction {

    @Override
    public void execute() throws Exception {
        try {
            for (Map<String, Object> keys : getSelectedKeys()) {
                Map<String, Object> values = MapFacade.getValues(getModelName(), keys, getView().getMembersNamesWithHidden());
                NativeManager.delete(getModelName(), keys, values);
            }
            getTab().deselectAll();
            resetDescriptionsCache();
        } catch (PersistenceException ex) {
            throw new RootCauseException(ex);
        }
    }
}
