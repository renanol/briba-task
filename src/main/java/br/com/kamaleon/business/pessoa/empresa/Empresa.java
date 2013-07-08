package br.com.kamaleon.business.pessoa.empresa;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.business.pessoa.Pessoa;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name="t_empresa")
@AttributeOverride(name="id", column=@Column(name="idEmpersa"))
@SequenceGenerator(name="gerador", sequenceName="s_empresa", allocationSize=1)
public class Empresa extends Pessoa{
	
	@NotBlank
    private String descricao;
    @Size(max = 100)
    private String observacao;
}
