package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,entry.id,entry.title,fileLink,fileDate,fileSize,authors,platforms,remakeYears,remakeStatus")
@View(name="Compact", members="id,entry.id,entry.title")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Remake {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Remake"))
    @ReadOnly
    private Integer id;

    @ManyToOne
    @ReferenceView("Compact")
    private Entry entry;

    @Column(length=250)
    @Required
    private String title;

    @Column(length=250)
    @Required
    private String fileLink;

    @Column(length=50)
    @Required
    private String fileDate;

    private Integer fileSize;

    @Column(length=250)
    private String authors;

    @Column(length=200)
    private String platforms;

    @Column(length=100)
    private String remakeYears;

    @Column(length=1000)
    @Stereotype("MEMO")
    private String remakeStatus;
}
