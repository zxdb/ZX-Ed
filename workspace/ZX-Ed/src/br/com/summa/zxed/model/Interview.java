package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="label.id,label.name,link,title,idiom.text,interviewer,interviewYear",
	 defaultOrder="${label.name}, ${link}")
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
    private Idiom idiom;

    @Column(length=200)
    private String interviewer;

    @Column(length=6)
    private Integer interviewYear;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
