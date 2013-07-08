
package br.com.kamaleon.business.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.kamaleon.business.endereco.Endereco;
import br.com.kamaleon.generic.CodigoKamaleon;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(mappedSuperclass = true)
public class Pessoa extends CodigoKamaleon {

    private String nome;
    @ManyToOne(fetch=FetchType.EAGER,cascade={ CascadeType.PERSIST, CascadeType.MERGE})
    @Cascade ({
        org.hibernate.annotations.CascadeType.SAVE_UPDATE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN
    }) 
    @JoinColumn(name = "IDENDERECO")
    private Endereco endereco;
    private String apelido;
    private String cpfCnpj;
    
}
