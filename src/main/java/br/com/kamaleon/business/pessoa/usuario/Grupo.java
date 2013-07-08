package br.com.kamaleon.business.pessoa.usuario;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.generic.CodigoKamaleon;
import br.com.kamaleon.generic.Status;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
@Table(name="t_grupo")
@AttributeOverride(name="id", column=@Column(name="idGrupo"))
@SequenceGenerator(name="gerador", sequenceName="s_grupo", allocationSize=1)
public class Grupo extends CodigoKamaleon {
	@NotBlank
	private String descricao;
	private Status status;
}
