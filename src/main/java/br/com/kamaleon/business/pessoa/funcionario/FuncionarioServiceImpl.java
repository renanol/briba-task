package br.com.kamaleon.business.pessoa.funcionario;



public  class FuncionarioServiceImpl implements FuncionarioService {
	
	
	public Funcionario buscarFuncionarioPorId(Long id){
		
		StringBuilder string = new StringBuilder();
		string.append("SELECT * FROM Funcionario f JOIN ");
		
        Funcionario funcionario = Funcionario.entityManager().createQuery(string.toString(), Funcionario.class).getSingleResult();

	
		return funcionario;
		
	}

}
