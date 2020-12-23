package br.com.summa.zxed.calc;

import org.openxava.calculators.*;

@lombok.Setter
public class ParentByPassCalculator extends ByPassCalculator {
    private static final long serialVersionUID = 1L;

    // FIXME: OpenXava 6.4.2 doesn't normally detect when a property has changed in a parent view.
    // See https://sourceforge.net/p/openxava/discussion/419690/thread/92bf2f2b6d/
    // Referencing "parent.id" is a work-around for this problem.
    protected Object parentId;
}
