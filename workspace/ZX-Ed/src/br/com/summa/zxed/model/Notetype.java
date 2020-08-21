package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Notetype {

	@Id
	@Column(length=1)
	private String id;

	@Column(length=100)
	@Required
    private String text;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
