package br.com.kamaleon.business.projeto.atividade;

import java.util.Date;

import br.com.kamaleon.business.pessoa.usuario.Usuario;
import br.com.kamaleon.business.projeto.Atividade;
import br.com.kamaleon.util.FuncoesUsuario;
import br.com.kamaleon.util.ValidadorUniversal;


public class AtividadeServiceImpl implements AtividadeService {
			
	public void saveAtividade(Atividade atividade) {
				
		StringBuilder stringBuilder = new StringBuilder();
		
		if(atividade.getCategoria() != null){
			stringBuilder.append("[ " + atividade.getCategoria().getDescricao());
		}
		if(atividade.getModulo() != null){	
			stringBuilder.append(" > " + atividade.getModulo().getDescricao());
		}
		if(atividade.getSubModulo() != null){
			stringBuilder.append(" > " + atividade.getSubModulo().getDescricao());
		}
		if(ValidadorUniversal.check(atividade.getTitulo())){
			stringBuilder.append(" ] "+atividade.getTitulo());
		}
		
		Usuario usuario = FuncoesUsuario.getUsuarioLogged();		
		
		atividade.setUsuarioCadastro(usuario);
		atividade.setTitulo(stringBuilder.toString());		 
		atividade.persist(); 
		
		adiconarHistorico(atividade, "Criada", usuario);
		
	}

	public void adiconarHistorico(Atividade atividade, String acao, Usuario usuario) {
		
		AtividadeHistorico atividadeHistorico = new AtividadeHistorico();
		atividadeHistorico.setAtividade(atividade);
		atividadeHistorico.setAcao(acao);
		atividadeHistorico.setUsuario(usuario);
		atividadeHistorico.setData(new Date());
		atividadeHistorico.persist();
	}
	
}
