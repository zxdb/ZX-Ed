package br.com.summa.zxed.act;

import java.util.*;

import org.openxava.actions.*;

import br.com.summa.zxed.sql.*;

@lombok.Setter
public class NativeDeleteSelectedAction extends TabBaseAction {

    @Override
    public void execute() throws Exception {
        for (Map<String, Object> keys : getSelectedKeys()) {
            NativeManager.delete(getTab().getModelName(), keys, Collections.EMPTY_MAP);
        }
        getTab().deselectAll();
        resetDescriptionsCache();
    }
}
