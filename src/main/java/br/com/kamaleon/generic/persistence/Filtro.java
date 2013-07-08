package br.com.kamaleon.generic.persistence;

import java.util.ArrayList;
import java.util.Iterator;

public interface Filtro {
	
public void clearFiltro();
	
	public void clearOrders();
	
	public void clearConditions();
	
	public void addOrder(String coluna, boolean asc);
	
	public void removeLastCondition();

	public void removeLastOrder();

	public void createConjunction();

	public void closeConjunction();

	public void createDisjunction();

	public void closeDisjunction();

	public void like(String coluna, Object valor);

	public void ilike(String coluna, Object valor);

	public void in(String coluna, ArrayList list);

	public void in(String coluna, Object[] objetos);

	public void in(String coluna, Iterator it);

	public void notIn(String coluna, ArrayList list);

	public void notIn(String coluna, Object[] objetos);

	public void notIn(String coluna, Iterator it);

	public void equals(String coluna, Object valor);
	
	public void equalsUpper(String coluna, String valor);

	public void notEquals(String coluna, Object valor);

	public void greaterThan(String coluna, Object valor);

	public void greaterOrEqualsThan(String coluna, Object valor);

	public void lessThan(String coluna, Object valor);

	public void lessOrEqualsThan(String coluna, Object valor);

	public void isNull(String coluna);

	public void isNotNull(String coluna);

	public void between(String coluna, Object valor1, Object valor2);
        
    public boolean hasConditions();
    
    public String getTipoCondicaoDate();
    
    public void setTipoCondicaoDate(String tipoCondicaoDate);
    
    public void likeUpper(String coluna, String valor);

}
