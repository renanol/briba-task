// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.kamaleon.business.pessoa.usuario;

import br.com.kamaleon.business.pessoa.usuario.Grupo;
import br.com.kamaleon.generic.Status;

privileged aspect Grupo_Roo_JavaBean {
    
    public String Grupo.getDescricao() {
        return this.descricao;
    }
    
    public void Grupo.setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Status Grupo.getStatus() {
        return this.status;
    }
    
    public void Grupo.setStatus(Status status) {
        this.status = status;
    }
    
}
