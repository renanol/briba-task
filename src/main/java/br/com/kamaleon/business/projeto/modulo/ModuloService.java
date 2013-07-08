package br.com.kamaleon.business.projeto.modulo;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { br.com.kamaleon.business.projeto.modulo.Modulo.class })
public interface ModuloService {
	public List<Modulo> listaModulosPorProjeto(String idProjeto);

}
