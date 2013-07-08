package br.com.kamaleon.business.pessoa.usuario;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name="t_equipe")
@AttributeOverride(name="id", column=@Column(name="idEquipe"))
@SequenceGenerator(name="gerador", sequenceName="s_equipe", allocationSize=1)
public class Equipe extends CodigoKamaleon {
	@NotEmpty
	private String descricao;
    @ManyToMany(cascade = CascadeType.ALL)
    @NotEmpty
    private Set<Usuario> usuarios = new HashSet<Usuario>();
}
