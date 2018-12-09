package br.com.summa.zxed.calc;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import br.com.summa.sol.util.*;

@lombok.Setter
public class NextAuthorSeq implements ICalculator {
    private static final long serialVersionUID = 1L;

    private Integer entryId;

    @Override
    public Object calculate() throws Exception {
        Query query = XPersistence.getManager().createQuery("select max(authorSeq) from Author where entry.id = :entryId");
        query.setParameter("entryId", entryId);
        return Nullables.coalesce((Integer)query.getSingleResult(), 0)+1;
    }
}
