package br.com.kamaleon.business.projeto.versao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.business.projeto.Atividade;
import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
@Table(name="t_versao")
@AttributeOverride(name="id", column=@Column(name="idVersao"))
@SequenceGenerator(name="gerador", sequenceName="s_versao", allocationSize=1)
public class Versao extends CodigoKamaleon{
	
	@NotBlank
	private String descricao;
	@NotNull
	@DateTimeFormat(style="M-")
	private Date data;
	@Transient
	private List<Atividade> atividades = new ArrayList<Atividade>();
	
}
