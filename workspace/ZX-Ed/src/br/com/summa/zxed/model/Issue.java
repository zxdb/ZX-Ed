package br.com.summa.zxed.model;

import static br.com.summa.sol.util.Nullables.*;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Issue {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Issue"))
    @ReadOnly
    private Integer id;

    @ManyToOne(optional=false)
    @DescriptionsList
    private Magazine magazine;

    @Column(length=4)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.dateYear"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer dateYear;

    @Column(length=2)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.dateMonth"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer dateMonth;

    @Column(length=2)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.dateDay"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer dateDay;

    @Column(length=6)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.volume"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer volume;

    @Column(length=6)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.number"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer number;

    @Column(length=100)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.special"),@PropertyValue(name="parentId", from="parent.id")})
    private String special;

    @Column(length=100)
    private String supplement;

    @Column(length=6)
    @Required
    @DefaultValueCalculator(value=IntegerCalculator.class, properties={ @PropertyValue(name="value", value="1")})
    private Integer coverPage;

    @ManyToOne
    private Issue parent;

    @Column(length=250)
    private String linkMask;

    @Column(length=250)
    private String archiveMask;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="issue", cascade=CascadeType.REMOVE)
    @ListProperties("referencetype.text,entry.id,entry.title,label.id,label.name,topic.id,topic.name,page,isOriginal")
    @XOrderBy("page,referencetype.text,entry.id,label.id,topic.id")
    private Collection<Magref> magazineReferences;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;

    @Transient
    @Depends("volume,number,dateYear,dateMonth,dateDay,special,supplement")
    public String getFancyName() {
        StringJoiner sj = new StringJoiner(" ");
        sj.setEmptyValue("?");
        if (volume != null) {
            sj.add("v."+volume);
        }
        if (number != null) {
            sj.add("#"+number);
        }
        if (dateYear != null) {
            sj.add(dateYear+(dateMonth != null ? "/"+dateMonth : "")+(dateDay != null ? "/"+dateDay : ""));
        }
        if (!isNullOrEmpty(special)) {
            sj.add("special \""+special+"\"");
        }
        if (!isNullOrEmpty(supplement)) {
            sj.add("supplement \""+supplement+"\"");
        }
        return sj.toString();
    }
}
