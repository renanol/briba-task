package br.com.kamaleon.business.projeto;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
@Table(name="t_projeto")
@AttributeOverride(name="id", column=@Column(name="idProjeto"))
@SequenceGenerator(name="gerador", sequenceName="s_projeto", allocationSize=1)
public class Projeto extends CodigoKamaleon {

	@NotEmpty
	private String descricao;
	private String paginaProjeto;	
	
}
