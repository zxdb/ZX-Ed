package br.com.summa.zxed.calc;

import static br.com.summa.sol.util.Nullables.*;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

@lombok.Setter
public class NextTagId implements ICalculator {
    private static final long serialVersionUID = 1L;

    private String tagtypeId;

    @Override
    public Object calculate() throws Exception {
        Query query = XPersistence.getManager().createQuery("select max(id) from Tag where tagtype.id = :tagtypeId");
        query.setParameter("tagtypeId", tagtypeId);
        return coalesce((Integer)query.getSingleResult(), 0)+1;
    }
}
