/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries;

import java.util.List;

import org.inbio.gwt.galleries.client.dto.DisplayInfo;
import org.inbio.gwt.galleries.client.dto.DisplayType;
import org.inbio.gwt.galleries.client.listener.BaseMediaListener;
import org.inbio.gwt.galleries.client.listener.MediaSlideListener;
import org.inbio.gwt.galleries.client.widget.BaseMedia;
import org.inbio.gwt.galleries.client.widget.ImageBig;
import org.inbio.gwt.galleries.client.widget.ImageThumbnail;
import org.inbio.gwt.galleries.client.widget.MediaSlide;
import org.inbio.gwt.galleries.client.widget.VideoFLV;
import org.inbio.m3s.gwt.client.rpcinterface.SearchMediaRPC;
import org.inbio.m3s.gwt.client.rpcinterface.SearchMediaRPCAsync;
import org.inbio.m3s.gwt.client.widgets.galleries.listener.NumberOfPagesListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This panel is the one who shows the results of the query, also is this object
 * in charge of the rpc call for the results
 * 
 * @author jgutierrez
 * 
 */
public class SearchResultPanel extends FlowPanel implements AsyncCallback, BaseMediaListener, MediaSlideListener {

	// TODO: deal with the number of elements returned and in what page to show
	// what
	public static final int BIG_SIZE = 0;

	public static final int THUMB_SIZE = 1;

	@SuppressWarnings("unused")
	private int actualLayout = THUMB_SIZE;

	// the list of QueryCriterias that where received when the user decide to
	// make a query
	private List query;
	
	//a list of DisplayInfo elements that matches the query parameters
	private List matchingDisplayInfoList;
	private int showingItem = -1;
	private MediaSlide mediaSlide;

	// private int actualPage;

	NumberOfPagesListener listener;

	private int totalPages;

	private int actualPage;

	private int maxResultsPerPage;

	private int totalResults;

	private int actualFirstResult;

	// private int actualLastResult;

	// rpc service
	private SearchMediaRPCAsync rpc;

	public SearchResultPanel(NumberOfPagesListener numberOfPagesListener) {
		initRPC();
		listener = numberOfPagesListener;
	}

	/**
	 * Makes the query via RPC, then the method onSuccess starts showing every
	 * image
	 * 
	 * @param querySummary
	 *            a list of searchTriplets
	 */
	public void showResults(List querySummary) {
		query = querySummary;

		rpc.getTotalResults(query, new AsyncCallback() {
			public void onFailure(Throwable caught) {
				Window.alert("Error en total de resultaos: "
						+ caught.getMessage());
			}

			public void onSuccess(Object result) {
				totalResults = ((Integer) result).intValue();
				updateControlVariables();

				callQuery();

			}
		});

	}

	/**
	 * This method is needed because has to be the SearchResultsPanel class the
	 * one that calls the getResults RPC method in order to be the same
	 * SearchResultsPanel class the one that implements the on sucess and on
	 * failure methods.
	 */
	private void callQuery() {
		rpc.getResults(query, actualFirstResult, maxResultsPerPage, this);
	}

	/**
	 * Updates the values related with quantity of results, number of pages,
	 * etc, this method has to fire a event to the searchMedia Manager (in this
	 * case that is the SearchMedia class), that event is realted with the total
	 * number of pages of the query, the idea is that the SearchMedia Manger
	 * updates the values that are shown.
	 * 
	 * This method is invocated in to places. The first one is on the onSuccess
	 * of the getTotalResults RPC. The second scenario is when the user changes
	 * the number of result per page, and the SearchMedia Manager will send a
	 * message to update the control variales
	 * 
	 */
	private void updateControlVariables() {

		totalPages = totalResults / maxResultsPerPage;

		if ((totalResults % maxResultsPerPage) != 0)
			totalPages = totalPages + 1;

		// The notify of the number of pages, also indicates that there is a
		// query done, and the results shown will be of the first page
		listener.notifyNumberOfPages(totalPages);

		actualPage = 1;

		actualFirstResult = 1;

	}

	/**
	 * 
	 * @param pageNumber
	 */
	public void showResultsForPage(int pageNumber) {
		if (query != null) {
			actualPage = pageNumber;

			actualFirstResult = (maxResultsPerPage * (actualPage - 1)) + 1;

			callQuery();
		}
	}

	/**
	 * @param actualSize
	 *            the actualSize to set
	 */
	public void setLayout(int actualSize) {
		this.actualLayout = actualSize;
		if (query != null) {
			callQuery();
		}
	}

	/**
	 * @param maxResultsPerPage
	 *            the maxResultsPerPage to set
	 */
	public void setMaxResultsPerPage(int maxResultsPerPage) {
		this.maxResultsPerPage = maxResultsPerPage;
		if (query != null) {
			updateControlVariables();
			callQuery();
		}
	}

	/**
	 * Cleand the previosly generated results
	 * 
	 */
	private void cleanResults() {
		int totalWidgets = getWidgetCount();
		for (int i = 0; i < totalWidgets; i++) {
			getWidget(0).removeFromParent();
		}
	}

	/**
	 * Event generated with the AsyncCallback
	 */
	public void onFailure(Throwable caught) {
		Window.alert("Error en la búsqueda: " + caught.getMessage());
	}

	/**
	 * Event generated with the AsyncCallback, this method expected to receive a
	 * List of DisplayInfo Objects
	 */
	public void onSuccess(Object result) {
		
		this.matchingDisplayInfoList = (List) result; //list of DisplayInfo
		//String literalMediaId;
		DisplayInfo di;
		cleanResults();
		ImageThumbnail it;

		for (int i = 0; i < matchingDisplayInfoList.size(); i++) {
			//literalMediaId = ((Integer) matchingDisplayInfoList.get(i)).toString();
			di = (DisplayInfo) matchingDisplayInfoList.get(i);
			//if (actualLayout == BIG_SIZE) {
						
			//} else if (actualLayout == THUMB_SIZE) {
				//this.add(new ImagesThumbnailOld((Integer) mediaIds.get(i)));
				di.setUrl(GWT.getModuleBaseURL()+"getImage?size=thumb&id=" + di.getId());
				this.add(it = new ImageThumbnail(di));
				it.setMediaListener(this);
			//}
		}
	}

	/**
	 * Init the RPC that all the class use
	 * 
	 */
	public void initRPC() {
		// (1) Initialize the RPC service.
		rpc = (SearchMediaRPCAsync) GWT.create(SearchMediaRPC.class);

		// (2) Specify the URL at which our service implementation is running.
		// Note that the target URL must reside on the same domain and port from
		// which the host page was served.
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "searchMediaRPC";
		endpoint.setServiceEntryPoint(moduleRelativeURL);
	}


	public void onEditMediaInfo(String Id) {
		// TODO Auto-generated method stub
		
	}

	public void onMediaSelected(Widget mediaWidget) {
		if (mediaWidget instanceof ImageThumbnail) {
			ImageThumbnail it = (ImageThumbnail) mediaWidget;
			DisplayInfo di = it.getDisplayInfo();
			
			for (int i = 0; i < this.matchingDisplayInfoList.size(); i++) {
				if (di.getId().equals((matchingDisplayInfoList.get(i)).toString())){
					this.showingItem = i;
					//Window.alert("media seleted index["+this.showingItem+"con Id["+di.getId()+"] y con Tipo["+di.getType()+"]");
				}
					
			}

			mediaSlide = new MediaSlide();
			mediaSlide.addMediaSlideListener(this);
			
			this.setNextMediaSlideElement(di);
		}
		
	}

	/**
	 * 
	 */
	public void onNextClicked() {
		this.showingItem = this.showingItem + 1;
		if (this.showingItem == this.matchingDisplayInfoList.size())
			this.showingItem = 0;

		this.setNextMediaSlideElement((DisplayInfo) this.matchingDisplayInfoList.get(this.showingItem));

		
	}
	

	public void onPreviousClicked() {
		this.showingItem = this.showingItem - 1;
		if (this.showingItem == -1)
			this.showingItem = this.matchingDisplayInfoList.size() - 1;

		this.setNextMediaSlideElement((DisplayInfo) this.matchingDisplayInfoList.get(this.showingItem));


	}

	/**
	 * Sets the next element in the Slide Show
	 * 
	 * @param next
	 */
	private void setNextMediaSlideElement(DisplayInfo next){
		BaseMedia baseMedia = null;

		//Window.alert("con Id["+next.getId()+"] y con Tipo["+next.getType()+"]");
		
		if(next.getType() ==  DisplayType.IMAGE){
			next.setUrl(GWT.getModuleBaseURL()+"getImage?size=big&id=" + next.getId());
			baseMedia = new ImageBig(next);			
		} else if(next.getType() == DisplayType.VIDEO){
			next.setUrl(GWT.getModuleBaseURL()+"getVideo?id=" + next.getId());
			baseMedia = new VideoFLV(next);			
		}
		

		if(baseMedia != null)
			mediaSlide.setMediaWidget(baseMedia);		
	}
	
	
	/**
	 * @return the matchingDisplayInfoList
	 */
	public List getMatchingDisplayInfoList() {
		return matchingDisplayInfoList;
	}

	/**
	 * @param matchingDisplayInfoList the matchingDisplayInfoList to set
	 */
	public void setMatchingDisplayInfoList(List matchingMediaIds) {
		this.matchingDisplayInfoList = matchingMediaIds;
	}


}
