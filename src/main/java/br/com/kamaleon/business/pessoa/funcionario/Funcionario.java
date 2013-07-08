package br.com.kamaleon.business.pessoa.funcionario;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.business.pessoa.Pessoa;
import br.com.kamaleon.business.pessoa.usuario.Usuario;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
@Table(name="t_funcionario")
@AttributeOverride(name="id", column=@Column(name="idFuncionario"))
@SequenceGenerator(name="gerador", sequenceName="s_funcionario", allocationSize=1)
public class Funcionario extends Pessoa {
	
	@NotNull	
	@DateTimeFormat(style="M-")
	private Date dataNascimento;
	@NotNull
	@DateTimeFormat(style="M-")
	private Date dataAdmissao;
	@ManyToOne
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;
	@NotNull
	@ManyToOne
	@JoinColumn(name="IDCARGO")
	private Cargo cargo;
	
	
}
