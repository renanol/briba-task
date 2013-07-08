package br.com.kamaleon.business.pessoa.funcionario;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name="t_cargo")
@AttributeOverride(name="id", column=@Column(name="idCargo"))
@SequenceGenerator(name="gerador", sequenceName="s_cargo", allocationSize=1)
public class Cargo {
	
	@NotBlank
    private String descricao;
}
