package br.com.summa.zxed.act;

import org.openxava.actions.*;
import org.openxava.validators.*;

import br.com.summa.zxed.sql.*;

public class NativeRemoveInCollectionAction extends RemoveElementFromCollectionAction {

    @Override
    public void execute() throws Exception {
        try {
            if (!getCollectionElementView().getKeyValuesWithValue().isEmpty()) {
                validateMinimum(1);
                String modelName = getCollectionElementView().getModelName();
                NativeManager.delete(modelName, getCollectionElementView().getKeyValues(), getCollectionElementView().getValues());
                commit();
                if (isEntityReferencesCollection()) {
                    addMessage("association_removed", modelName, getCollectionElementView().getParent().getModelName());
                }
                else {
                    addMessage("aggregate_removed", modelName);
                }
            }
            getCollectionElementView().setCollectionEditingRow(-1);
            getCollectionElementView().clear();
            getView().recalculateProperties();
            closeDialog();
        } catch (ValidationException ex) {
            addErrors(ex.getErrors());
        }
    }
}
