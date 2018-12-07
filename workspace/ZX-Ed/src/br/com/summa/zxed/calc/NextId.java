package br.com.summa.zxed.calc;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import br.com.summa.sol.util.*;

@lombok.Setter
public class NextId implements ICalculator {
    private static final long serialVersionUID = 1L;

    private String modelName;

    @Override
    public Object calculate() throws Exception {
        Query query = XPersistence.getManager().createQuery("select max(id) from "+modelName);
        return Nullables.coalesce((Integer)query.getSingleResult()+1, 1);
    }
}
