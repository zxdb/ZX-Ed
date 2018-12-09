package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(defaultOrder="${entry.id} asc, ${authorSeq} asc")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(AuthorKey.class)
public class Author {
    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Label label;

    @ManyToOne
    @ReferenceView("Compact")
    private Label team;

    @Column(length=6)
    @DefaultValueCalculator(value=NextAuthorSeq.class, properties=@PropertyValue(name="entryId", from="entry.id"))
    @Required
    @Min(1)
    private Integer authorSeq;

    @ListProperties("roletype.text")
    @lombok.ToString.Exclude
    @OneToMany(mappedBy="author")
    private Collection<Role> roles;
    
    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
