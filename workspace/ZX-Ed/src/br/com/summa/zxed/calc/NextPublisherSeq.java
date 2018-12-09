package br.com.summa.zxed.calc;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import br.com.summa.sol.util.*;

@lombok.Setter
public class NextPublisherSeq implements ICalculator {
    private static final long serialVersionUID = 1L;

    private Integer entryId;
    private Integer releaseSeq;

    @Override
    public Object calculate() throws Exception {
        Query query = XPersistence.getManager().createQuery("select max(publisherSeq) from Publisher where entry.id = :entryId and releaseSeq = :releaseSeq");
        query.setParameter("entryId", entryId);
        query.setParameter("releaseSeq", releaseSeq);
        return Nullables.coalesce((Integer)query.getSingleResult(), 0)+1;
    }
}
