package br.com.summa.zxed.act;

import java.util.*;

import javax.ejb.*;

import org.openxava.actions.*;
import org.openxava.model.*;

import br.com.summa.zxed.sql.*;

public class NativeSaveElementInCollectionAction extends SaveElementInCollectionAction {

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
		if (isEntity) {
			Map<String, Object> keyValues = getCollectionElementView().getKeyValues();
			keyValues.put(getMetaCollection().getMetaReference().getRole(), containerKey);
			values.put(getMetaCollection().getMetaReference().getRole(), containerKey);
			NativeManager.insert(getCollectionElementView().getModelName(), keyValues, values);
	        addMessage("entity_created_and_associated", getCollectionElementView().getModelName(), getCollectionElementView().getParent().getModelName());
		} else {
			MapFacade.createAggregate(getCollectionElementView().getModelName(), containerKey, getMetaCollection().getName(), values);
			addMessage("aggregate_created",	getCollectionElementView().getModelName(), getCollectionElementView().getParent().getModelName());
		}
	}
}
