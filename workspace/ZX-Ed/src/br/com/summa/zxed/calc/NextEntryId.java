package br.com.summa.zxed.calc;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import br.com.summa.sol.util.*;

@lombok.Setter
public class NextEntryId implements ICalculator {
    private static final long serialVersionUID = 1L;

    protected String title; // force calculation even when other properties are never set
    private Integer genretypeId;
    private Integer machinetypeId;

    public Object calculate() throws Exception {
        int baseId = (machinetypeId != null && machinetypeId >= 11 && machinetypeId <= 13) ? 4000000 :
            (genretypeId != null && genretypeId >= 84 && genretypeId <= 90) ? 2000000 :
            (genretypeId != null && genretypeId >= 91 && genretypeId <= 108) ? 1000000 : 0;
        Query query = XPersistence.getManager().createQuery("select max(id) from Entry where id < :maxId");
        query.setParameter("maxId", baseId+1000000);
        return Nullables.coalesce((Integer)query.getSingleResult(), baseId)+1;
    }
}
