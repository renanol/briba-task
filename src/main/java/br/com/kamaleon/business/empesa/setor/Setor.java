package br.com.kamaleon.business.empesa.setor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.kamaleon.generic.CodigoKamaleon;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name="t_setor")
@AttributeOverride(name="id", column=@Column(name="idSetor"))
@SequenceGenerator(name="gerador", sequenceName="s_setor", allocationSize=1)
public class Setor extends CodigoKamaleon {
	@NotEmpty
    private String descricao;
}
