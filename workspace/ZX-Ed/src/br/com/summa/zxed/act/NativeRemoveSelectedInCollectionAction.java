package br.com.summa.zxed.act;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;

import br.com.summa.zxed.sql.*;

public class NativeRemoveSelectedInCollectionAction extends RemoveSelectedInCollectionAction {

	@Override
	protected void removeElement(Map keys) throws Exception {
        String modelName = getCollectionElementView().getModelName();
        Map<String, Object> values = MapFacade.getValues(modelName, keys, getCollectionElementView().getMembersNamesWithHidden());
        NativeManager.delete(modelName, keys, values);
	}
}
