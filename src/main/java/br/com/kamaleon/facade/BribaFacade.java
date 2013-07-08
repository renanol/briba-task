package br.com.kamaleon.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.configurable.RooConfigurable;

import br.com.kamaleon.business.endereco.EnderecoService;
import br.com.kamaleon.business.pessoa.cliente.ClienteService;
import br.com.kamaleon.business.pessoa.funcionario.CargoService;
import br.com.kamaleon.business.pessoa.usuario.GrupoService;
import br.com.kamaleon.business.pessoa.usuario.UsuarioService;
import br.com.kamaleon.business.projeto.atividade.AtividadeService;
import br.com.kamaleon.business.projeto.categoria.CategoriaService;
import br.com.kamaleon.business.projeto.modulo.ModuloService;
import br.com.kamaleon.business.projeto.modulo.SubModuloService;
import br.com.kamaleon.business.projeto.prioridade.PrioridadeService;
import br.com.kamaleon.business.projeto.situacao.SituacaoService;
import br.com.kamaleon.business.projeto.versao.VersaoService;
import br.com.kamaleon.generic.Status;

@RooConfigurable
public class BribaFacade {
	
	private static BribaFacade bribaFacade = null;
	
	@Autowired
	private CargoService cargoService;
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private AtividadeService atividadeService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ModuloService moduloService;
	@Autowired
	private SubModuloService subModuloService;
	@Autowired
	private PrioridadeService prioridadeService;
	@Autowired
	private SituacaoService situacaoService;
	@Autowired
	private VersaoService versaoService;
	

	private BribaFacade(){}

	public static BribaFacade getInstance(){

		if(bribaFacade == null){
			synchronized (BribaFacade.class) {
				bribaFacade = new BribaFacade();			

			}
		}
		return bribaFacade;
	}

}
