package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="label.id,label.name,link,articletype.text,title,language.text,writer,dateYear",
defaultOrder="${articletype.text}, ${label.name}, ${dateYear}, ${link}")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(ArticleKey.class)
public class Article {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Label label;

    @Id
    @Column(length=200)
    private String link;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Articletype articletype;

    @Column(length=200)
    private String title;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Language language;

    @Column(length=200)
    private String writer;

    @Column(length=6)
    private Integer dateYear;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
