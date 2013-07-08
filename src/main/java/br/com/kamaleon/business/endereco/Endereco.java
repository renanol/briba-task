package br.com.kamaleon.business.endereco;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
@Table(name="t_endereco")
@AttributeOverride(name="id", column=@Column(name="idEndereco"))
@SequenceGenerator(name="gerador", sequenceName="s_endereco", allocationSize=1)
public class Endereco extends CodigoKamaleon {
	
	private String logradouro;
	private String bairro;
	private String numero;
	private String cep;
	private String estado;
	private String cidade;
	
}
