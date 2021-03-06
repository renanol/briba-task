// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.kamaleon.business.projeto.prioridade;

import br.com.kamaleon.business.projeto.prioridade.Prioridade;
import br.com.kamaleon.business.projeto.prioridade.PrioridadeService;
import java.util.List;

privileged aspect PrioridadeService_Roo_Service {
    
    public abstract long PrioridadeService.countAllPrioridades();    
    public abstract void PrioridadeService.deletePrioridade(Prioridade prioridade);    
    public abstract Prioridade PrioridadeService.findPrioridade(Long id);    
    public abstract List<Prioridade> PrioridadeService.findAllPrioridades();    
    public abstract List<Prioridade> PrioridadeService.findPrioridadeEntries(int firstResult, int maxResults);    
    public abstract void PrioridadeService.savePrioridade(Prioridade prioridade);    
    public abstract Prioridade PrioridadeService.updatePrioridade(Prioridade prioridade);    
}
