package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="label.id,label.name,link,title,language.text,interviewer,interviewYear",
	 defaultOrder="${label.name}, ${interviewYear}, ${link}")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(InterviewKey.class)
public class Interview {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Label label;

    @Id
    @Column(length=200)
    private String link;

    @Column(length=200)
    private String title;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Language language;

    @Column(length=200)
    private String interviewer;

    @Column(length=6)
    private Integer interviewYear;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
