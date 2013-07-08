package br.com.kamaleon.business.projeto.atividade;

import org.springframework.roo.addon.layers.service.RooService;

import br.com.kamaleon.business.pessoa.usuario.Usuario;
import br.com.kamaleon.business.projeto.Atividade;

@RooService(domainTypes = { br.com.kamaleon.business.projeto.Atividade.class })
public interface AtividadeService {
	
	public void adiconarHistorico(Atividade atividade, String acao, Usuario usuario);
	
}
