package br.com.summa.zxed.calc;

import static br.com.summa.sol.util.Nullables.coalesce;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

@lombok.Setter
public class NextMemberSeq implements ICalculator {
    private static final long serialVersionUID = 1L;

    private Integer tagId;
    private String tagtypeId;

    @Override
    public Object calculate() throws Exception {
        if ("S".equals(tagtypeId)) {
            Query query = XPersistence.getManager().createQuery("select max(memberSeq) from Member where tag.id = :tagId");
            query.setParameter("tagId", tagId);
            return coalesce((Integer)query.getSingleResult(), 0)+1;
        }
        return null;
    }
}
