package br.com.summa.zxed.act;

import java.util.*;

import javax.ejb.*;

import org.openxava.actions.*;

import br.com.summa.zxed.sql.*;

public class NativeSaveInCollectionAction extends SaveElementInCollectionAction {

    @Override
    protected void saveCollectionElement(Map containerKey) throws Exception {
        if (getCollectionElementView().isEditable()) {
            boolean isEntity = isEntityReferencesCollection();
            Map<String, Object> values = getValuesToSave();
			try {
                NativeManager.update(getCollectionElementView().getModelName(), getCollectionElementView().getKeyValues(), values);
                addMessage(isEntity?"entity_modified":"aggregate_modified", getCollectionElementView().getModelName());
            } catch (ObjectNotFoundException ex) {
                create2(values, isEntity, containerKey);
            }
        } else {
            // unreachable code?
            throw new RuntimeException();
        }
    }

    protected void create2(Map<String, Object> values, boolean isEntity, Map<String, Object> containerKey) throws Exception {
        validateMaximum(1);
        Map<String, Object> keyValues = getCollectionElementView().getKeyValues();
        keyValues.put(getMetaCollection().getMetaReference().getRole(), containerKey);
        values.put(getMetaCollection().getMetaReference().getRole(), containerKey);
        NativeManager.insert(getCollectionElementView().getModelName(), keyValues, values);
        addMessage(isEntity?"entity_created_and_associated":"aggregate_created", getCollectionElementView().getModelName(), getCollectionElementView().getParent().getModelName());
    }
}
