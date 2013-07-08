package br.com.kamaleon.business.projeto.atividade;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.business.pessoa.usuario.Usuario;
import br.com.kamaleon.business.projeto.Atividade;
import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name="t_atividadeHistorico")
@AttributeOverride(name="id", column=@Column(name="idAtividadeHistorico"))
@SequenceGenerator(name="gerador", sequenceName="s_atividadeHistorico", allocationSize=1)
public class AtividadeHistorico extends CodigoKamaleon {
	
	@ManyToOne
	private Atividade atividade;
	private String horasTrabalhada;
	private String progresso;
	@Size(max=2000)
	private String descricao;
	@DateTimeFormat(style="M-")
	private Date data;
	@ManyToOne
	private Usuario usuario;
	private String acao;
	
}
