/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.gwt.client.widgets.galleries.listener.SearchCriteriaItemListener;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The graphical element that makes a visual summary of the selected
 * filter/criteria for the search. This widget controls the unselected of a
 * filter/criteria, and implements methods for managing the summary of
 * SearchCriteriaTriplets
 * 
 * @author jgutierrez
 * 
 */
public class SearchCriteriaSummary extends Composite implements
		SearchCriteriaItemListener {

	private VerticalPanel main;

	/**
	 * Constructor
	 * 
	 */
	public SearchCriteriaSummary() {

		main = new VerticalPanel();

		initWidget(main);
	}

	/**
	 * Graphically adds a new triplete to the User Interface, creates a new
	 * SearchCriteriaItem widget for the graphical management.
	 * 
	 * @param triplete
	 *            filer/criteria selected for the search
	 */
	public void addCriteriaItem(SearchCriteriaTriplet triplete) {
		SearchCriteriaItem item = new SearchCriteriaItem(triplete, this);
		main.add(item);

	}

	/**
	 * Graphically removes a given SearchCriteriaItem (widget) from the
	 * Filter/Criteria list and also from the summary.
	 * 
	 * @param item
	 *            the selected SearchCriteriaItem that's going to be removed
	 */
	public void removeCriteria(SearchCriteriaItem item) {

		SearchCriteriaItem child;

		// Checks every son of the Widget and compares one by one until gets the
		// equal then removes it
		for (int i = 0; i < main.getWidgetCount(); i++) {
			child = (SearchCriteriaItem) main.getWidget(i);
			if (child.equals(item)) {
				child.removeFromParent();
				break;
			}
		}
	}

	/**
	 * Makes a summary of the Filters/Criterias selected for the search.
	 * 
	 * @return a SearchCriteriaTriplet List with all the elements selected
	 */
	public List<SearchCriteriaTriplet> getSummary() {
		// List of SearchCriteriaTriplet elements
		List<SearchCriteriaTriplet> result = new ArrayList<SearchCriteriaTriplet>();
		SearchCriteriaItem child;

		// Checks every son of the Widget and compares one by one until gets the
		// equal then removes it
		for (int i = 0; i < main.getWidgetCount(); i++) {
			child = (SearchCriteriaItem) main.getWidget(i);
			result.add(child.getTriplete());
		}

		return result;
	}

	/**
	 * Manages the removeCriteria event, basically what this means is that has
	 * to graphically remove the given as parameter Widget, and this also
	 * removes the element from the summary.
	 */
	public void removeCriteriaItem(SearchCriteriaItem eventObject) {
		SearchCriteriaItem item = (SearchCriteriaItem) eventObject;
		removeCriteria(item);

	}

}
