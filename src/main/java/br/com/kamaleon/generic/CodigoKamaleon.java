package br.com.kamaleon.generic;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord
@RooToString
@RooJpaEntity(mappedSuperclass = true, versionField="")
public class CodigoKamaleon{
		
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gerador")
	private Long id;
	
	

	
}
