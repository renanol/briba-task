package br.com.kamaleon.business.projeto.prioridade;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name="t_prioridade")
@AttributeOverride(name="id", column=@Column(name="idPrioridade"))
@SequenceGenerator(name="gerador", sequenceName="s_prioridade", allocationSize=1)
public class Prioridade {
	@NotEmpty
	private String descricao;
}
