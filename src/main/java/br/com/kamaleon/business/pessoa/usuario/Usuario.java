package br.com.kamaleon.business.pessoa.usuario;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.generic.CodigoKamaleon;
import br.com.kamaleon.generic.Status;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
@Table(name="t_usuario")
@AttributeOverride(name="id", column=@Column(name="idUsuario"))
@SequenceGenerator(name="gerador", sequenceName="s_usuario", allocationSize=1)
public class Usuario extends CodigoKamaleon {
	@Column(unique=true)
	@NotBlank
	private String login;
	@NotBlank
	private String senha;
	@NotBlank
	@Email
	private String email;
	private Status status;
	@ManyToOne
	@JoinColumn(name="IDGRUPO")
	private Grupo grupo;
	private String urlFoto;
		
}
