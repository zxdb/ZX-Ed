package br.com.summa.zxed.act;

import org.openxava.actions.*;

public class NativeCreateNewInCollectionAction extends CreateNewElementInCollectionAction {

    @Override
    public void execute() throws Exception {
        if (isParentSaved()) {
            super.execute();
        } else {
            addError("zxed_parent_not_saved");
        }
    }
}
