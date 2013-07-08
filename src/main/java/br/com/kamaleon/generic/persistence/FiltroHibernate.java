package br.com.kamaleon.generic.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class FiltroHibernate implements Filtro {

	private static final long serialVersionUID = 1680656102564936858L;

	private ArrayList<Order> listOrder;

	private ArrayList<Criterion> listCondicao; // lista principal de condicoes que serao usadas para a filtragem

	private ArrayList<Junction> listCondicoesAninhadas;

	private String tipoCondicaoDate;
	
	private static final int QTD_MAXIMA_LISTA_IN = 1000;

	public FiltroHibernate() {
		listOrder = new ArrayList<Order>();
		listCondicao = new ArrayList<Criterion>();
		listCondicoesAninhadas = new ArrayList<Junction>();
		//setTipoCondicaoDate(Condicao.DATE);
	}

	public void clearFiltro() {
		listOrder.clear();
		listCondicao.clear();
		listCondicoesAninhadas.clear();
		//setTipoCondicaoDate(Condicao.DATE);
	}

	public void clearOrders() {
		listOrder.clear();
	}

	public void clearConditions() {
		listCondicao.clear();
		listCondicoesAninhadas.clear();
	}

	public void addOrder(String coluna, boolean asc) {
		if (asc) {
			listOrder.add(Order.asc(coluna));
		} else {
			listOrder.add(Order.desc(coluna));
		}
	}

	public void removeLastCondition() {
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.remove(listCondicoesAninhadas.size() - 1);
		} else if (listCondicao.size() > 0) {
			listCondicao.remove(listCondicao.size() - 1);
		}
	}

	public void removeLastOrder() {
		if (listOrder.size() > 0) {
			listOrder.remove(listOrder.size() - 1);
		}
	}

	public void createConjunction() {
		Conjunction cj = Restrictions.conjunction();
		listCondicoesAninhadas.add(cj);
	}

	public void closeConjunction() {
		Junction junction = listCondicoesAninhadas
				.remove(listCondicoesAninhadas.size() - 1);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(
					junction);
		} else {
			listCondicao.add(junction);
		}
	}

	public void createDisjunction() {
		Disjunction dj = Restrictions.disjunction();
		listCondicoesAninhadas.add(dj);
	}

	public void closeDisjunction() {
		Junction junction = listCondicoesAninhadas
				.remove(listCondicoesAninhadas.size() - 1);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(
					junction);
		} else {
			listCondicao.add(junction);
		}
	}

	public void like(String coluna, Object valor) {
		valor = verificaSeDate(valor);
		// case sensitive
		Criterion c = Restrictions.like(coluna, valor);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void ilike(String coluna, Object valor) {
		valor = verificaSeDate(valor);
		// case insensitive
		Criterion c = Restrictions.ilike(coluna, valor);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void in(String coluna, ArrayList list) {
		if (list.size() > 0 && list.size() > QTD_MAXIMA_LISTA_IN) 
		{
			ArrayList lista1 = (ArrayList) list.clone();
			ArrayList lista2 = new ArrayList();
			for (int i = QTD_MAXIMA_LISTA_IN; i > 0; i--) 
			{
				lista2.add(lista1.remove(i-1));
			}
			
			createDisjunction();
			in(coluna, lista2);
			in(coluna, lista1);
			closeDisjunction();
		}
		else
		{
			Criterion c = Restrictions.in(coluna, list);
			if (list.size() > 0) {
				if (listCondicoesAninhadas.size() > 0) {
					listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
				} else {
					listCondicao.add(c);
				}
			}
		}
	}
	
	public void in(String coluna, Object[] objetos) {
		if (objetos.length > 0 && objetos.length > QTD_MAXIMA_LISTA_IN) 
		{
			ArrayList lista1 = null;//ConversorTipos.getArrayList(objetos);
			ArrayList lista2 = new ArrayList();
			
			for (int i = QTD_MAXIMA_LISTA_IN; i > 0; i--) 
			{
				lista2.add(lista1.remove(i-1));
			}
			
			createDisjunction();
			in(coluna, lista2);
			in(coluna, lista1);
			closeDisjunction();
		}
		else
		{
			Criterion c = Restrictions.in(coluna, objetos);
			if (objetos.length > 0) {
				if (listCondicoesAninhadas.size() > 0) {
					listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
				} else {
					listCondicao.add(c);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void in(String coluna, Iterator it) {
		//in(coluna, ConversorTipos.getArrayList(it));
	}

	public void notIn(String coluna, ArrayList list) {
		if (list.size() > 0 && list.size() > QTD_MAXIMA_LISTA_IN) 
		{
			ArrayList lista1 = (ArrayList) list.clone();
			ArrayList lista2 = new ArrayList();
			for (int i = QTD_MAXIMA_LISTA_IN; i > 0; i--) 
			{
				lista2.add(lista1.remove(i-1));
			}
			notIn(coluna, lista2);
			notIn(coluna, lista1);
		}
		else
		{
			Criterion c = Restrictions.not(Restrictions.in(coluna, list));
			if (listCondicoesAninhadas.size() > 0) {
				listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
			} else {
				listCondicao.add(c);
			}
		}
	}
	
	public void notIn(String coluna, Object[] objetos) {
		if (objetos.length > 0 && objetos.length > QTD_MAXIMA_LISTA_IN) 
		{
			ArrayList lista1 = null;//ConversorTipos.getArrayList(objetos);
			ArrayList lista2 = new ArrayList();
			for (int i = QTD_MAXIMA_LISTA_IN; i > 0; i--) 
			{
				lista2.add(lista1.remove(i-1));
			}
			notIn(coluna, lista2);
			notIn(coluna, lista1);
		}
		else
		{
			Criterion c = Restrictions.not(Restrictions.in(coluna, objetos));
			if (listCondicoesAninhadas.size() > 0) {
				listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
			} else {
				listCondicao.add(c);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void notIn(String coluna, Iterator it) {
		if (it.hasNext()) {
			//notIn(coluna, ConversorTipos.getArrayList(it));
		}
	}
	
	//TODO implementar verificação de tipos!
	public void equals(String coluna, Object valor) {
		valor = verificaSeDate(valor);
		Criterion c = Restrictions.eq(coluna, valor);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}
	
	public void equalsUpper(String coluna, String valor) {
		//Criterion c = Restrictions.eq(coluna, valor);
		Criterion c = Restrictions.sqlRestriction("upper(this_."+coluna+") = ?", valor.toUpperCase(),
				Hibernate.STRING);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void notEquals(String coluna, Object valor) {
		valor = verificaSeDate(valor);
		Criterion c = Restrictions.ne(coluna, valor);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void greaterThan(String coluna, Object valor) {
		valor = verificaSeDate(valor);
		Criterion c = Restrictions.gt(coluna, valor);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void greaterOrEqualsThan(String coluna, Object valor) {
		valor = verificaSeDate(valor);
		Criterion c = Restrictions.ge(coluna, valor);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void lessThan(String coluna, Object valor) {
		valor = verificaSeDate(valor);
		Criterion c = Restrictions.lt(coluna, valor);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void lessOrEqualsThan(String coluna, Object valor) {
		valor = verificaSeDate(valor);
		Criterion c = Restrictions.le(coluna, valor);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void isNull(String coluna) {
		Criterion c = Restrictions.isNull(coluna);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void isNotNull(String coluna) {
		Criterion c = Restrictions.isNotNull(coluna);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void between(String coluna, Object valor1, Object valor2) {
		valor1 = verificaSeDate(valor1);
		valor2 = verificaSeDate(valor2);
		Criterion c = Restrictions.between(coluna, valor1, valor2);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}

	public void likeUpper(String coluna, String valor) {
		Criterion c = Restrictions.sqlRestriction("upper(this_."+coluna+") like ?", "%" + valor.toUpperCase() + "%",
				Hibernate.STRING);
		if (listCondicoesAninhadas.size() > 0) {
			listCondicoesAninhadas.get(listCondicoesAninhadas.size() - 1).add(c);
		} else {
			listCondicao.add(c);
		}
	}
	
	public String getTipoCondicaoDate() {
		return tipoCondicaoDate;
	}

	public void setTipoCondicaoDate(String tipoCondicaoDate) {
		this.tipoCondicaoDate = tipoCondicaoDate;
	}

	/**
	 * Obtem o objeto usado para o filtro do Hibernate
	 * 
	 * @param session
	 * @param classObj
	 * @return
	 */
	public <T> Criteria getCriteria(Class<T> classObj) {
		HibernateUtil util = new HibernateUtil();
		Criteria ct = util.getSession().createCriteria(classObj);

		for (Order order : listOrder) {
			ct.addOrder(order);
		}

		for (Criterion criterion : listCondicao) {
			ct.add(criterion);
		}

		return ct;
	}

	public boolean hasConditions() {
		return listCondicao.size() > 0;
	}

	private Object verificaSeDate(Object valor) {
		try {
			if (valor instanceof Date) {
				if (getTipoCondicaoDate().equals(null)) {
					//valor = FuncoesData.getData(FuncoesData.formatarDataSemHora((Date) valor));
				} else {
					//valor = FuncoesData.getDataHora(FuncoesData.formatarDataComHora((Date) valor));
				}
			}
		} catch (Exception e) {
			
		}
		return valor;
	}
}
