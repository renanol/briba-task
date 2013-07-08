package br.com.kamaleon.business.projeto;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.web.multipart.MultipartFile;

import br.com.kamaleon.business.pessoa.cliente.Cliente;
import br.com.kamaleon.business.pessoa.usuario.Usuario;
import br.com.kamaleon.business.projeto.atividade.StatusAtividade;
import br.com.kamaleon.business.projeto.categoria.Categoria;
import br.com.kamaleon.business.projeto.modulo.Modulo;
import br.com.kamaleon.business.projeto.modulo.SubModulo;
import br.com.kamaleon.business.projeto.prioridade.Prioridade;
import br.com.kamaleon.business.projeto.versao.Versao;
import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
@Table(name="t_atividade")
@AttributeOverride(name="id", column=@Column(name="idAtividade"))
@SequenceGenerator(name="gerador", sequenceName="s_atividade", allocationSize=1)
public class Atividade extends CodigoKamaleon{
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="IDPROJETO")
	private Projeto projeto;
	@NotBlank
	private String titulo;	
	@NotBlank
	@Size(max=2000)
	private String descricao;
	@NotNull
	@DateTimeFormat(style="M-")
	private Date dataInicio;	
	@ManyToOne
	@JoinColumn(name="IDUSUARIOCADASTRO")
	private Usuario usuarioCadastro;
	@ManyToOne
	@JoinColumn(name="IDUSUARIOALOCADO")
	private Usuario usuarioAlocado;
	@NotNull
	@ManyToOne
	@JoinColumn(name="IDMODULO")
	private Modulo modulo;
	@NotNull
	@ManyToOne
	@JoinColumn(name="IDSUBMODULO")
	private SubModulo subModulo;
	@ManyToOne
	@JoinColumn(name="IDVERSAO")
	private Versao versao;
	@ManyToOne
	@JoinColumn(name="IDCLIENTE")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="IDCATEGORIA")
	private Categoria categoria;
	@NotNull
	@ManyToOne
	@JoinColumn(name="IDPRIORIDADE")
	private Prioridade prioridade;
	private boolean commitado;
	private double tempoEstimado;
	private double tempoGasto;
	@Size(max=1000)
	private String comentario;
	private String atividadePai;	
	@DateTimeFormat(style="M-")
	private Date dataPrevista;
	private StatusAtividade statusAtividade;	
	private String progresso;
	@Transient
	private MultipartFile arquivo;
	@Size(max=128)
	private String caminhoArquivo;	
	

	
	
}
