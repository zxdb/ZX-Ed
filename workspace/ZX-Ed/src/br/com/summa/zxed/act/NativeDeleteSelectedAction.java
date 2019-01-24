package br.com.summa.zxed.act;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;

import br.com.summa.zxed.sql.*;

public class NativeDeleteSelectedAction extends TabBaseAction {

    @Override
    public void execute() throws Exception {
        for (Map<String, Object> keys : getSelectedKeys()) {
        	Map<String, Object> values = MapFacade.getValues(getModelName(), keys, getView().getMembersNamesWithHidden());
            NativeManager.delete(getModelName(), keys, values);
        }
        getTab().deselectAll();
        resetDescriptionsCache();
    }
}
