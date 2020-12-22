package br.com.summa.zxed.calc;

import static br.com.summa.sol.util.Nullables.*;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

@lombok.Setter
public class NextReleaseSeq implements ICalculator {
    private static final long serialVersionUID = 1L;

    private Integer entryId;

    @Override
    public Object calculate() throws Exception {
        Query query = XPersistence.getManager().createQuery("select max(releaseSeq) from Release where entry.id = :entryId");
        query.setParameter("entryId", entryId);
        return coalesce((Integer)query.getSingleResult(), -1)+1;
    }
}
