package br.com.kamaleon.util;

public class Condicao {
	
	private static final long serialVersionUID = 5055974123065888625L;

	private String atributo;
    private String operador;
    private String operadorLogico;
    private String valor;
    private String tipoValor;
    private String alias;
    
    public static String INTEGER = "INTEGER";
    public static String INT = "INT";
    public static String LONG = "LONG";
    public static String FLOAT = "FLOAT";
    public static String STRING = "STRING";
    public static String DATE = "DATE";
    public static String DATETIME = "DATETIME";
    public static String TIME = "TIME";
    public static String BLOB = "BLOB";
    public static String NOT_SET = "NOT_SET";

    public static String AND = "AND";
    public static String OR = "OR";
    
    /** Cria uma nova instancia de Condicao. */
    public Condicao()
    {
        setAtributo(new String());
        setOperador(new String());
        setOperadorLogico(AND);
        setValor(new String());
        setTipoValor(NOT_SET); 
        setAlias("");
    }
    
    /** Cria uma nova instancia de Condicao, inicializando cada um dos campos. */
    public Condicao(String novoAtributo, String novoOperador, String novoValor)
    {
        this(novoAtributo, novoOperador, novoValor, NOT_SET, "", AND);
    }
    
    /** Cria uma nova instancia de Condicao, inicializando cada um dos campos. */
    public Condicao(String novoAtributo, String novoOperador, String novoValor,
            String novoTipoValor)
    {
        this(novoAtributo, novoOperador, novoValor, novoTipoValor, "", AND);
    }
    
    /** Cria uma nova instancia de Condicao, inicializando cada um dos campos. */
    public Condicao(String novoAtributo, String novoOperador, String novoValor,
            String novoTipoValor, String novoAlias)
    {
        setAtributo(novoAtributo);
        setOperador(novoOperador);
        setOperadorLogico(AND);
        setValor(novoValor);
        setTipoValor(novoTipoValor);
        setAlias(novoAlias);
    }
    
    /** Cria uma nova instancia de Condicao, inicializando cada um dos campos. */
    public Condicao(String novoAtributo, String novoOperador, String novoValor,
            String novoTipoValor, String novoAlias, String novoOperadorLogico)
    {
        setAtributo(novoAtributo);
        setOperador(novoOperador);
        setOperadorLogico(novoOperadorLogico);
        setValor(novoValor);
        setTipoValor(novoTipoValor);
        setAlias(novoAlias);
    }
    
    /** Retorna o nome do alias desta condicao. */
    public String getAlias() { return alias; }
    
    /** Define o nome do alias da condicao. */
    public void setAlias(String novoAlias) { alias = novoAlias; }    
    
    /** Retorna o nome do atributo desta condicao. */
    public String getAtributo() { return atributo; }
    
    /** Define o nome do atributo da condicao. */
    public void setAtributo(String novoAtributo) { atributo = novoAtributo; }
    
    /** Retorna o operador utilizado nesta condicao. */
    public String getOperador() { return operador; }
    
    /** Define o operador utilizado na condicao. */
    public void setOperador(String novoOperador) { operador = novoOperador; }
    
    /** Retorna o operador logico utilizado nesta condicao. */
    public String getOperadorLogico() { return operadorLogico; }
    
    /** Define o operador logico utilizado na condicao. */
    public void setOperadorLogico(String novoOperadorLogico) { operadorLogico = novoOperadorLogico; }
    
    /** Retorna o valor do atributo sendo comparado nesta condicao. */
    public String getValor() { return valor; }
    
    /** Define o valor do atributo sendo comparado nesta condicao. */
    public void setValor(String novoValor) { valor = novoValor; }

    /** Retorna o tipo do valor do atributo sendo comparado nesta condicao. */
    public String getTipoValor() { return tipoValor; }
    
    /** Define o tipo do valor do atributo sendo comparado nesta condicao. */
    public void setTipoValor(String novoTipoValor) { tipoValor = novoTipoValor; }

}
