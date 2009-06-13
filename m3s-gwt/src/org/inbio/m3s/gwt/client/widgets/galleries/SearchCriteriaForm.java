/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries;

import java.util.List;

import org.inbio.m3s.gwt.client.widgets.galleries.listener.SearchFilterListener;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.SearchListener;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class manages the search criteria widget, the results from this class
 * are the inputs of the galleries
 * 
 * @author jgutierrez
 */
public class SearchCriteriaForm extends Composite implements ClickListener,
		SearchFilterListener {

	private HorizontalPanel main;

	private VerticalPanel eastSide;

	private SearchFilter searchFilterWidget;

	private VerticalPanel westSide;

	private Label generalExplanationText;

	private SearchCriteriaSummary criteriaSummary;

	private Label speficHelpText;

	private Button search;

	private SearchListener searchListener;

	/**
	 * Constructor
	 * 
	 * @param listener
	 */
	public SearchCriteriaForm(SearchListener listener) {

		main = new HorizontalPanel();
		main.setSpacing(10);

		// WEST SIDE OF THE SCEEN
		westSide = new VerticalPanel();
		westSide.setSpacing(15);

		westSide.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		generalExplanationText = new Label(
				"Por favor agregue al menos un criterio de Búsqueda...");
		generalExplanationText.setStyleName("searchCriteria-generalText");
		westSide.add(generalExplanationText);

		westSide.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		searchFilterWidget = new SearchFilter(this);
		westSide.add(searchFilterWidget);

		speficHelpText = new Label(
				"Aqui debe ir una explicación del filtro y criterio de búsqueda seleccionado");
		speficHelpText.setStyleName("searchCriteria-specificText");
		westSide.add(speficHelpText);

		main.add(westSide);

		// EAST SIDE OF THE SCEEN
		eastSide = new VerticalPanel();
		eastSide.setSpacing(10);

		search = new Button("buscar", this);
		eastSide.add(search);

		criteriaSummary = new SearchCriteriaSummary();
		eastSide.add(criteriaSummary);

		main.add(eastSide);

		// The rest of things
		searchListener = listener;

		initWidget(main);

	}

	/**
	 * Manages the "Search Button" click
	 * 
	 * TODO: has to be done well
	 */
	public void onClick(Widget sender) {

		List allCriterias = criteriaSummary.getSummary();
		searchListener.executeSearch(allCriterias);

	}

	/**
	 * Evento se ejecuta cada vez que se escoge algún filtro y para agregarlo en
	 * el widget que muestra el resumen de la consutla
	 */
	public void filterSelected(SearchCriteriaTriplet triplete) {
		criteriaSummary.addCriteriaItem(triplete);

	}

	/**
	 * Cuando un filtro se elije pero por algún motivo no puede ser usado..
	 * 
	 */
	public void invalidFilter(String cause) {
		Window.alert("filtro invalido: " + cause);

	}

}
