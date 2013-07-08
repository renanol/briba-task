package br.com.kamaleon.generic.persistence;


public class RepositorioException extends Exception {
	private static final long serialVersionUID = 8896257075961234349L;

	/**
     * Cria uma nova instancia de <code>RepositorioException</code> sem
     * uma mensagem de detalhe.
     */
    public RepositorioException() {
	}
    
    
    /**
     * Constroi uma nova instancia de <code>RepositorioException</code> 
     * com a mensage de detalhe especificada.
     * @param msg a mensagem de detalhe.
     */
    public RepositorioException(String msg) {
		super(msg);
	}

    /**
     * Constroi uma nova instancia de <code>RepositorioException</code> 
     * com o erro original de detalhe especificada.
     * @param error o erro original.
     */
    public RepositorioException(Throwable error) {
		super(error);
	}
}
