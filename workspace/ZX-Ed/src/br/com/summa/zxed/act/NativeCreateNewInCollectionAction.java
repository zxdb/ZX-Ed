package br.com.summa.zxed.act;

import org.openxava.actions.*;
import org.openxava.view.*;

public class NativeCreateNewInCollectionAction extends CreateNewElementInCollectionAction {

    public void execute() throws Exception {
        if (isParentSaved2()) {
            super.execute();
        } else {
            addError("zxed_parent_not_saved");
        }
    }

    protected boolean isParentSaved2() {
        View view = getCollectionElementView().getParent();
        if (getView() == view) {
            if (view.isKeyEditable()) {
                return false;
            }
        } else {
            if (view.getKeyValuesWithValue().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
