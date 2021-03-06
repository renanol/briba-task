// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.kamaleon.business.projeto.versao;

import br.com.kamaleon.business.projeto.versao.Versao;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Versao_Roo_Jpa_ActiveRecord {
    
    public static long Versao.countVersaos() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Versao o", Long.class).getSingleResult();
    }
    
    public static List<Versao> Versao.findAllVersaos() {
        return entityManager().createQuery("SELECT o FROM Versao o", Versao.class).getResultList();
    }
    
    public static Versao Versao.findVersao(Long id) {
        if (id == null) return null;
        return entityManager().find(Versao.class, id);
    }
    
    public static List<Versao> Versao.findVersaoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Versao o", Versao.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Versao Versao.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Versao merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
