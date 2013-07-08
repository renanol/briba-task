package br.com.kamaleon.business.projeto.modulo;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { br.com.kamaleon.business.projeto.modulo.SubModulo.class })
public interface SubModuloService {
	public List<SubModulo> listaSubModulosPorProjeto(String idModulo);

}
