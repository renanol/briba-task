// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.kamaleon.business.projeto.atividade;

import br.com.kamaleon.business.projeto.atividade.AtividadeHistorico;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

privileged aspect AtividadeHistorico_Roo_Jpa_Entity {
    
    declare @type: AtividadeHistorico: @Entity;
    
    @Version
    @Column(name = "version")
    private Integer AtividadeHistorico.version;
    
    public Integer AtividadeHistorico.getVersion() {
        return this.version;
    }
    
}
