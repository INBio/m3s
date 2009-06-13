/**
 * 
 */
package org.inbio.m3s.gwt.client;

import java.util.List;

import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaForm;
import org.inbio.m3s.gwt.client.widgets.galleries.SearchResultPanel;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.LayoutSelectedListener;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.NumberOfPagesListener;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.PaginationListener;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.ResultsPerPageSelectedListener;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.SearchListener;
import org.inbio.m3s.gwt.client.widgets.galleries.ui.LayoutSelector;
import org.inbio.m3s.gwt.client.widgets.galleries.ui.Pagination;
import org.inbio.m3s.gwt.client.widgets.galleries.ui.ResultsPerPage;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * @author jgutierrez
 * 
 */
public class SearchMedia extends Composite implements LayoutSelectedListener,
		ResultsPerPageSelectedListener, PaginationListener, SearchListener,
		NumberOfPagesListener {

	private HTMLPanel main;

	// Title: label, string & Div
	private String title = "Busqueda de Im\u00E1genes";// this has to

	private String titleDiv = HTMLPanel.createUniqueId();

	// Layout selector
	private LayoutSelector layoutSelectorWidget;

	private String layoutSelectorDiv = HTMLPanel.createUniqueId();

	// Results per page
	private ResultsPerPage resultsPerPageWidget;

	private String resultsPerPageDiv = HTMLPanel.createUniqueId();

	// Pagination widget
	private Pagination upperPaginationWidget = new Pagination(this);

	private String upperPaginationDiv = HTMLPanel.createUniqueId();

	private Pagination bottomPaginationWidget = new Pagination(this);

	private String bottomPaginationDiv = HTMLPanel.createUniqueId();

	// Tabs: Container, tabpanel and div
	private DockPanel tabPanelContainer = new DockPanel();

	private TabPanel tabPanel;

	private String tabPanelDiv = HTMLPanel.createUniqueId();

	// Tabs of the tabpanel
	private final int QUERY_CREATOR_TAB = 0;

	private SearchCriteriaForm searchCriteriaWidget;

	private final int QUERY_RESULT_TAB = 1;

	private SearchResultPanel searchResults = new SearchResultPanel(this);

	private String username;

	public SearchMedia() {
		// the MainPanel
		main = new HTMLPanel(
				"<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
						+ "<tr>"
						+ "<td width=\"85%\" align=\"left\" valign=\"top\" bgcolor=\"#4B619A\" class=\"MainPanel\">"
						+ "<tr>"
						+ "<td height=\"18\">"

						+ "<!-- Zona Superior de la Pagina...-->"
						+ "<table width=\"100%\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\">"

						+ "<!-- Titulo de la Pagina-->"
						+ "<tr>"
						+ "<td>"
						+ "<div id=\""
						+ titleDiv
						+ "\"></div>"
						+ "</td>"
						+ "<!-- Selector del Layout de la pagina-->"
						+ "<td width=\"320\" align=\"right\"> "
						+ "<div id=\""
						+ layoutSelectorDiv
						+ "\"></div> "
						+ "</td>"
						+ "</tr>"

						+ "<!-- Paginación-->"
						+ "<tr>"
						+ "<td align=\"left\" valign=\"top\">"
						+ "<div id=\""
						+ upperPaginationDiv
						+ "\"></div>"
						+ "</td>"
						+ "<!-- Numero de Resultados por pagina-->"
						+ "<td valign=\"top\" class=\"imaName\">"
						+ "<div id=\""
						+ resultsPerPageDiv
						+ "\"></div>"
						+ "</td>"
						+ "</tr>"
						+ "</table>"

						+ "<!-- Zona del panel principal -->"
						+ "<table width=\"100%\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\" >"
						+ "<tr>" + "<td>" + "<div id=\"" + tabPanelDiv
						+ "\"></div>" + "</td>" + "</tr>" + "</table>"

						+ "<!-- Paginación-->" + "<tr>"
						+ "<td align=\"center\" valign=\"middle\">"
						+ "<div id=\"" + bottomPaginationDiv + "\"></div>"
						+ "</td>" + "</tr>"

						+ "<!-- Fin-->" + "</table>" + "</td>" + "</tr>"
						+ "</td>" + "</tr>" + "</table>");

		initWidget(main);

		setUsername(LoginManager.getUserName());

		// adds all the stuff here...

		// Adding widgets to the divs defined in the mainPanel:
		Label theTitle = new Label(title);
		theTitle.setStyleName("MainPanel-Title");
		main.add(theTitle, titleDiv);

		// Layout selector
		layoutSelectorWidget = new LayoutSelector(this);
		main.add(layoutSelectorWidget, layoutSelectorDiv);

		// Paginatiors...
		// upperPaginationWidget = new Pagination(this);
		// upperPaginationWidget.setPages(10);
		main.add(upperPaginationWidget, upperPaginationDiv);
		// bottomPaginationWidget = new Pagination(this);
		// bottomPaginationWidget.setPages(10);
		main.add(bottomPaginationWidget, bottomPaginationDiv);

		// Results per Page
		resultsPerPageWidget = new ResultsPerPage("8", "16", "24", this);
		searchResults.setMaxResultsPerPage(8);
		main.add(resultsPerPageWidget, resultsPerPageDiv);

		// init the tab panel
		tabPanel = new TabPanel();
		tabPanelContainer.setWidth("100%");
		tabPanelContainer.setHeight("500px");
		tabPanelContainer.add(tabPanel, DockPanel.NORTH);
		main.add(tabPanelContainer, tabPanelDiv);

		// sets the tabs init
		searchCriteriaWidget = new SearchCriteriaForm(this);
		tabPanel.add(searchCriteriaWidget, "Criterios de Busqueda");
		tabPanel.add(searchResults, "Resultados de Busqueda");

		tabPanel.selectTab(this.QUERY_CREATOR_TAB);

	}

	/**
	 * Fires this event when a new Layout is selected, and sends a message to
	 * the search Results panel to reload the graphical elements in the desired
	 * layout
	 */
	public void layoutSelected(int layoutConstant) {
		if (layoutConstant == LayoutSelector.BIG_LAYOUT) {
			searchResults.setLayout(SearchResultPanel.BIG_SIZE);
		} else if (layoutConstant == LayoutSelector.THUMP_LAYOUT) {
			searchResults.setLayout(SearchResultPanel.THUMB_SIZE);
		}
		// searchResults.reloadResults();
	}

	/**
	 * event fired when a layout is selected on the Results per page Widget
	 */
	public void resultsPerPageSelected(int results) {
		searchResults.setMaxResultsPerPage(results);
	}

	/**
	 * event fired when the a page is selected in the Pagination Widget
	 */
	public void pageSelected(int page) {
		searchResults.showResultsForPage(page);

	}

	/**
	 * event fired when the searchCriteria tab if completed with information and
	 * the user click "search" button
	 */
	public void executeSearch(List querySummary) {
		tabPanel.selectTab(this.QUERY_RESULT_TAB);
		searchResults.showResults(querySummary);

	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Event fired when the SearchResultsPanel receives a number of results for
	 * a query, then that class makes tha calculation of the number of pages,
	 * based on the selected results per query, and generates this event.
	 */
	public void notifyNumberOfPages(int pages) {
		upperPaginationWidget.setPages(pages);
		// setActualPage(int page);
		bottomPaginationWidget.setPages(pages);
	}
}
