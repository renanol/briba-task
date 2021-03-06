// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.kamaleon.business.pessoa.usuario;

import br.com.kamaleon.business.pessoa.usuario.Grupo;
import br.com.kamaleon.business.pessoa.usuario.GrupoServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GrupoServiceImpl_Roo_Service {
    
    declare @type: GrupoServiceImpl: @Service;
    
    declare @type: GrupoServiceImpl: @Transactional;
    
    public long GrupoServiceImpl.countAllGrupoes() {
        return Grupo.countGrupoes();
    }
    
    public void GrupoServiceImpl.deleteGrupo(Grupo grupo) {
        grupo.remove();
    }
    
    public Grupo GrupoServiceImpl.findGrupo(Long id) {
        return Grupo.findGrupo(id);
    }
    
    public List<Grupo> GrupoServiceImpl.findAllGrupoes() {
        return Grupo.findAllGrupoes();
    }
    
    public List<Grupo> GrupoServiceImpl.findGrupoEntries(int firstResult, int maxResults) {
        return Grupo.findGrupoEntries(firstResult, maxResults);
    }
    
    public void GrupoServiceImpl.saveGrupo(Grupo grupo) {
        grupo.persist();
    }
    
    public Grupo GrupoServiceImpl.updateGrupo(Grupo grupo) {
        return grupo.merge();
    }
    
}
