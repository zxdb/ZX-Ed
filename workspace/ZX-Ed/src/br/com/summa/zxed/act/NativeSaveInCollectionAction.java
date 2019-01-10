package br.com.summa.zxed.act;

import java.util.*;

import javax.ejb.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.util.*;
import org.openxava.view.*;

import br.com.summa.zxed.sql.*;

public class NativeSaveInCollectionAction extends SaveElementInCollectionAction {

    protected Map<String, Object> saveIfNotExists(View view) throws Exception {
        if (getView() == view ? view.isKeyEditable() : view.getKeyValuesWithValue().isEmpty()) {
            throw new XavaException("zxed_parent_not_saved");
        }
        return view.getKeyValues();
    }

    protected void saveCollectionElement(Map containerKey) throws Exception {
        if (getCollectionElementView().isEditable()) {
            boolean isEntity = isEntityReferencesCollection();
            Map<String, Object> values = getValuesToSave();
            try {
                MapFacade.setValues(getCollectionElementView().getModelName(), getCollectionElementView().getKeyValues(), values);
                addMessage(isEntity?"entity_modified":"aggregate_modified", getCollectionElementView().getModelName());
            } catch (ObjectNotFoundException ex) {
                create2(values, isEntity, containerKey);
            }
        } else {
            validateMaximum(1);
            associateEntity(getCollectionElementView().getKeyValues());
            addMessage("entity_associated" , getCollectionElementView().getModelName(), getCollectionElementView().getParent().getModelName());
        }
    }

    private void create2(Map<String, Object> values, boolean isEntity, Map<String, Object> containerKey) throws Exception {
        validateMaximum(1);
        Map<String, Object> keyValues = getCollectionElementView().getKeyValues();
        keyValues.put(getMetaCollection().getMetaReference().getRole(), containerKey);
        values.put(getMetaCollection().getMetaReference().getRole(), containerKey);
        NativeManager.insert(getCollectionElementView().getModelName(), keyValues, values);
        if (isEntity) {
            addMessage("entity_created_and_associated", getCollectionElementView().getModelName(), getCollectionElementView().getParent().getModelName());
        } else {
            addMessage("aggregate_created",	getCollectionElementView().getModelName(), getCollectionElementView().getParent().getModelName());
        }
    }
}