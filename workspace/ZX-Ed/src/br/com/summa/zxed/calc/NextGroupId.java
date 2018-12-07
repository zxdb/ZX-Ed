package br.com.summa.zxed.calc;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import br.com.summa.sol.util.*;

@lombok.Setter
public class NextGroupId implements ICalculator {
    private static final long serialVersionUID = 1L;

    private String grouptypeId;

    public Object calculate() throws Exception {
        Query query = XPersistence.getManager().createQuery("select max(id) from Group where grouptype.id = :grouptypeId");
        query.setParameter("grouptypeId", grouptypeId);
        return Nullables.coalesce((Integer)query.getSingleResult()+1, 1);
    }
}
