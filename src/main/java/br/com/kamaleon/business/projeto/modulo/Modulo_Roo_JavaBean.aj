// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.kamaleon.business.projeto.modulo;

import br.com.kamaleon.business.projeto.Projeto;
import br.com.kamaleon.business.projeto.modulo.Modulo;

privileged aspect Modulo_Roo_JavaBean {
    
    public String Modulo.getDescricao() {
        return this.descricao;
    }
    
    public void Modulo.setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Projeto Modulo.getProjeto() {
        return this.projeto;
    }
    
    public void Modulo.setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
}
