/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries;

import org.inbio.m3s.gwt.client.dto.util.SearchFilterEntity;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.SearchCriteriaItemListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget that handles the searchTriplete information in a graphical way and
 * this is used for the SearchCriteriaSummary widget.
 * 
 * @author jgutierrez
 */
public class SearchCriteriaItem extends Composite implements ClickListener {

	private HorizontalPanel main;

	private SearchCriteriaTriplet triplete;

	private SearchCriteriaItemListener listener;

	/**
	 * Constructor of the graphical Widget
	 * 
	 * @param triplete
	 *            the SearchCriteriaTriplete to be represented
	 */
	public SearchCriteriaItem(SearchCriteriaTriplet triplete,
			SearchCriteriaItemListener listener) {

		this.setTriplete(triplete);

		main = new HorizontalPanel();
		main.setSpacing(3);

		//Label filter = new Label(SearchFilterValues.getLiteralValue(triplete
		//		.getFilter()));
		Label filter = new Label(SearchFilterEntity.getKeywordById(triplete.getFilter()));
		main.add(filter);
		Label criteria = new Label(SearchCriteriaValues
				.getLiteralValue(triplete.getCriteria()));
		main.add(criteria);
		Label value = new Label(triplete.getValue());
		main.add(value);
		Image removeImage = new Image(GWT.getModuleBaseURL()
				+ "/images/deleteItem.png");
		removeImage.addClickListener(this);
		main.add(removeImage);

		this.listener = listener;
		initWidget(main);
	}

	/**
	 * @param triplete
	 *            the triplete to set
	 */
	public void setTriplete(SearchCriteriaTriplet triplete) {
		this.triplete = triplete;
	}

	/**
	 * @return the triplete
	 */
	public SearchCriteriaTriplet getTriplete() {
		return triplete;
	}

	/**
	 * Manages the click over the "Remove" image of the widget, this fires a new
	 * removeCriteriaItem event that has to be managed in the father of the
	 * widget. The event fired (removeCriteriaItem) sends as parameter the
	 * SearchCriteriaItem that has to be removed
	 * 
	 */
	public void onClick(Widget sender) {
		listener.removeCriteriaItem(this);

	}

}
