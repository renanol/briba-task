package br.com.kamaleon.business.projeto.categoria;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name="t_categoria")
@AttributeOverride(name="id", column=@Column(name="idCategoria"))
@SequenceGenerator(name="gerador", sequenceName="s_categoria", allocationSize=1)
public class Categoria extends CodigoKamaleon {
	
	@NotBlank
	private String descricao;
	
}
