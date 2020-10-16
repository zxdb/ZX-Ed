package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,entry.id,entry.title,label.id,label.name,notetype.text,section,beginText")
@View(name="Compact", members="id,entry.id,entry.title,label.id,label.name,notetype.text,section,beginText")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Note {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Note"))
    @ReadOnly
    private Integer id;

    @ManyToOne
    @ReferenceView("Compact")
    private Entry entry;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Notetype notetype;

    @Column(length=100)
    @Required
    private String section;

    @Column(length=50000)
    @Required
    @Stereotype("MEMO")
    private String text;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;

    @Transient
    @Depends("text")
    public String getBeginText() {
    	return text.length() > 180 ? text.substring(0, 180)+"..." : text;
    }
}
