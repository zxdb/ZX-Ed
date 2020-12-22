package br.com.summa.zxed.calc;

import static br.com.summa.sol.util.Nullables.*;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

@lombok.Setter
public class NextMemberSeq implements ICalculator {
    private static final long serialVersionUID = 1L;

    private Integer groupId;
    private String grouptypeId;

    @Override
    public Object calculate() throws Exception {
        if ("S".equals(grouptypeId)) {
            Query query = XPersistence.getManager().createQuery("select max(seriesSeq) from Member where group.id = :groupId");
            query.setParameter("groupId", groupId);
            return coalesce((Integer)query.getSingleResult(), 0)+1;
        }
        return null;
    }
}
