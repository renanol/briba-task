// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.kamaleon.business.empesa;

import br.com.kamaleon.business.empesa.EmpresaService;
import br.com.kamaleon.business.pessoa.empresa.Empresa;
import java.util.List;

privileged aspect EmpresaService_Roo_Service {
    
    public abstract long EmpresaService.countAllEmpresas();    
    public abstract void EmpresaService.deleteEmpresa(Empresa empresa);    
    public abstract Empresa EmpresaService.findEmpresa(Long id);    
    public abstract List<Empresa> EmpresaService.findAllEmpresas();    
    public abstract List<Empresa> EmpresaService.findEmpresaEntries(int firstResult, int maxResults);    
    public abstract void EmpresaService.saveEmpresa(Empresa empresa);    
    public abstract Empresa EmpresaService.updateEmpresa(Empresa empresa);    
}