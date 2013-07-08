package br.com.kamaleon.business.pessoa.cliente;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.kamaleon.business.pessoa.Pessoa;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name="t_cliente")
@AttributeOverride(name="id", column=@Column(name="idCliente"))
@SequenceGenerator(name="gerador", sequenceName="s_cliente", allocationSize=1)
public class Cliente extends Pessoa {
	
}
