package br.com.kamaleon.business.projeto.modulo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
@Table(name="t_submodulo")
@AttributeOverride(name="id", column=@Column(name="idSubmodulo"))
@SequenceGenerator(name="gerador", sequenceName="s_submodulo", allocationSize=1)
public class SubModulo extends CodigoKamaleon{	
	
	@NotEmpty
	private String descricao;
	@NotNull
	@ManyToOne
	@JoinColumn(name="IDMODULO")
	private Modulo modulo;
	
}
