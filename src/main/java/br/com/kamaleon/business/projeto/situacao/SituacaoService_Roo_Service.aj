// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.kamaleon.business.projeto.situacao;

import br.com.kamaleon.business.projeto.situacao.Situacao;
import br.com.kamaleon.business.projeto.situacao.SituacaoService;
import java.util.List;

privileged aspect SituacaoService_Roo_Service {
    
    public abstract long SituacaoService.countAllSituacaos();    
    public abstract void SituacaoService.deleteSituacao(Situacao situacao);    
    public abstract Situacao SituacaoService.findSituacao(Long id);    
    public abstract List<Situacao> SituacaoService.findAllSituacaos();    
    public abstract List<Situacao> SituacaoService.findSituacaoEntries(int firstResult, int maxResults);    
    public abstract void SituacaoService.saveSituacao(Situacao situacao);    
    public abstract Situacao SituacaoService.updateSituacao(Situacao situacao);    
}
