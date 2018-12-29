package br.com.summa.zxed.act;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.validators.*;

import br.com.summa.zxed.sql.*;

public class NativeRemoveSelectedInCollectionAction extends RemoveSelectedInCollectionAction {

    public void execute() throws Exception {
        try {
            List<Object> selectedOnes = getSelectedObjects();
            validateMinimum(selectedOnes.size());
            if (!selectedOnes.isEmpty()) {
                for (Object entity : selectedOnes) {
                    String modelName = getCollectionElementView().getModelName();
                    Map<String, Object> keyValues = MapFacade.getKeyValues(modelName, entity);
                    Map<String, Object> values = MapFacade.getValues(modelName, keyValues, getCollectionElementView().getMembersNamesWithHidden());
                    NativeManager.delete(modelName, keyValues, values);
                }
                commit();
                addMessage();
                getView().recalculateProperties();
                getCollectionElementView().collectionDeselectAll();
                getCollectionElementView().refreshCollections();
            }
        } catch (ValidationException ex) {
            addErrors(ex.getErrors());
        }
    }
}
