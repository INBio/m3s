/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries;

import org.inbio.m3s.gwt.client.dto.util.SearchFilterEntity;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.SearchFilterListener;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * The graphical element that allows the user to selected the desired
 * filter/criteria for the search. The constructor of this class has to be done
 * in a unwired way. When a filter/criteria is selected the widget fires a
 * fileterSelected event.
 * 
 * @author jgutierrez
 * 
 */
public class SearchFilter extends Composite implements ClickListener {

	private HorizontalPanel main;

	private ListBox filter;

	private ListBox criteria;

	private TextBox value;

	private Button addFilter;

	private SearchFilterListener listener;

	/**
	 * Constructor The filters and criterias are wired!!
	 * 
	 * @param listener
	 */
	public SearchFilter(SearchFilterListener listener) {

		main = new HorizontalPanel();
		main.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		main.setSpacing(5);

		// filters
		filter = new ListBox();
		filter.addItem(SearchFilterEntity.MEDIA_ID.getKeyword(),
				String.valueOf(SearchFilterEntity.MEDIA_ID.getId()));
				//SearchFilterValues.MEDIA_ID.toString());

		filter.addItem(SearchFilterEntity.TAXON.getKeyword(),
				String.valueOf(SearchFilterEntity.TAXON.getId()));

		filter.addItem(SearchFilterEntity.AUTHOR.getKeyword(),
				String.valueOf(SearchFilterEntity.AUTHOR.getId()));
		
		filter.addItem(SearchFilterEntity.KEYWORD.getKeyword(),
				String.valueOf(SearchFilterEntity.KEYWORD.getId()));

		filter.addItem(SearchFilterEntity.PROJECT.getKeyword(),
				String.valueOf(SearchFilterEntity.PROJECT.getId()));
		
		filter.addItem(SearchFilterEntity.FAMILY.getKeyword(),
				String.valueOf(SearchFilterEntity.FAMILY.getId()));
		
		filter.addItem(SearchFilterEntity.GENUS.getKeyword(),
				String.valueOf(SearchFilterEntity.GENUS.getId()));
		
		filter.addItem(SearchFilterEntity.SPECIES.getKeyword(),
				String.valueOf(SearchFilterEntity.SPECIES.getId()));
		
		
		main.add(filter);

		// criterias
		criteria = new ListBox();
		criteria.addItem(SearchCriteriaValues
				.getLiteralValue(SearchCriteriaValues.IS),
				SearchCriteriaValues.IS.toString());
		main.add(criteria);
		/*
		criteria.addItem(SearchCriteriaValues.
				getLiteralValue(SearchCriteriaValues.LIKE),
				SearchCriteriaValues.LIKE.toString());
		 */
		value = new TextBox();
		main.add(value);

		addFilter = new Button("agregar", this);
		main.add(addFilter);

		this.listener = listener;
		initWidget(main);
	}

	/**
	 * Event fired when a user wants to add the filter/criteria selected, if the
	 * requiered values are ok then a filterSelected event is fired.
	 * 
	 * @param sender
	 *            is the Button widget that was clicked.
	 * @return void but fires a filterSelected event with a new
	 *         SearchCriteriaTriplet object as param
	 * 
	 * TODO: improve the verification of filter/criteria
	 */
	public void onClick(Widget sender) {

		String valueItem = value.getText();

		if (valueItem.compareTo("") != 0) {
			Integer filterItem = new Integer(filter.getValue(filter
					.getSelectedIndex()));
			Integer criteriaItem = new Integer(criteria.getValue(criteria
					.getSelectedIndex()));

			SearchCriteriaTriplet triplete = new SearchCriteriaTriplet(
					filterItem, criteriaItem, valueItem);

			listener.filterSelected(triplete);

		} else {
			listener.invalidFilter("Información insuficiente "
					+ "para establecer filtro");
		}
	}

}
