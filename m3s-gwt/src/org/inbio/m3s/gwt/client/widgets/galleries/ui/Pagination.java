/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.ui;

import org.inbio.m3s.gwt.client.widgets.galleries.listener.PaginationListener;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author jgutierrez
 * 
 */
public class Pagination extends Composite implements ClickListener {

	private HorizontalPanel main;

	private Label text;

	private HorizontalPanel pages;

	private PaginationListener paginationListener;

	private int totalPages;
	
	private int actualPage;

	private static int TOTAL_PAGES_TO_SHOW = 10;
	private static String LESS = "anteriores";
	private static String MORE = "siguientes";

	public Pagination(PaginationListener paginationListener) {
		main = new HorizontalPanel();
		main.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		main.setSpacing(10);

		// text = new Label("Páginas: ");
		text = new Label();

		pages = new HorizontalPanel();
		pages.setSpacing(5);

		main.add(text);
		main.add(pages);

		this.paginationListener = paginationListener;

		initWidget(main);
	}

	/**
	 * Sets (for the first time) the number of pages 
	 * to be shown on the pagination widget
	 * 
	 * @param pagesNumber
	 */
	public void setPages(int pagesNumber) {

		if (pagesNumber > 0){
			this.totalPages = pagesNumber;
			this.actualPage = 1;
			updateShownPages();
			
			text.setText("Mostrando "+Pagination.TOTAL_PAGES_TO_SHOW+" paginas de un total de "+this.totalPages+".");
		}
	}

	/**
	 * Determines the range of pages to be shown. And then invoke the 
	 * generateUIElement with the correct parameters 
	 * 
	 */
	private void updateShownPages(){	
		
		//se va a mostrar todas las paginas pues son menos del total a mostrar
		if (this.totalPages <= Pagination.TOTAL_PAGES_TO_SHOW) {
		  generateUIElement(1, this.totalPages, false, false);
		
		//la pagina a mostrar está en el último bloque de imágenes medio de otras...  
		} else if(this.actualPage + (Pagination.TOTAL_PAGES_TO_SHOW-1) >= this.totalPages){
			generateUIElement(this.totalPages - (Pagination.TOTAL_PAGES_TO_SHOW-1), this.totalPages, true, false);
		
		//la imagen seleccionada esta en medio de otras, pero no esta en el bloque final de images.
		} else { 
			generateUIElement(this.actualPage, this.actualPage+ (Pagination.TOTAL_PAGES_TO_SHOW-1), true, true);
		}
		
	}
	

	/**
	 * 
	 * @param firstPageNumber
	 *          the initial page number to show
	 * @param lastPageNumber
	 *          the last page number to show
	 * @param LESS
	 *          show the LESS pages message ("anteriores")
	 * @param MORE
	 *          show the MORE pages message ("siguientes")
	 */
	private void generateUIElement(int firstPageNumber, int lastPageNumber,	boolean less, boolean more) {
		pages.removeFromParent();
		pages = new HorizontalPanel();
		pages.setSpacing(5);
		Label item;

		if(less){
			item = new Label(Pagination.LESS);
			item.addClickListener(this);
			item.setStyleName("pagination-text");
			pages.add(item);
		}
			
		for (int i = firstPageNumber; i <= lastPageNumber; i++) {
			item = new Label(String.valueOf(i));
			item.addClickListener(this);

			if (i == this.actualPage)
				item.setStyleName("pagination-text-selected");
			else
				item.setStyleName("pagination-text");

			pages.add(item);
		}
		
		if(more){
			item = new Label(Pagination.MORE);
			item.addClickListener(this);
			item.setStyleName("pagination-text");
			pages.add(item);
		}

		main.add(pages);
	}
	
	/**
	 *Calcultes the new actual page.
	 * 
	 */
	private void calculateNewSelectedPage(String text){
	
		if(text.equals(Pagination.LESS)){
			
			if(this.actualPage - Pagination.TOTAL_PAGES_TO_SHOW < 1){
				this.actualPage = 1;
			
			} else {
				this.actualPage =  this.actualPage - Pagination.TOTAL_PAGES_TO_SHOW;
			}
			
		} else { // == this.more
			if(this.actualPage + Pagination.TOTAL_PAGES_TO_SHOW > this.totalPages){
				this.actualPage = this.totalPages;
			
			} else {
				this.actualPage =  this.actualPage + Pagination.TOTAL_PAGES_TO_SHOW;
			}
		}
		
	}

	/**
	 * Page label that was clicked!
	 */
	public void onClick(Widget sender) {
		Label item = (Label) sender;
		item.setStyleName("pagination-text-selected");
		try {
			paginationListener.pageSelected(Integer.parseInt(item.getText()));
			this.actualPage = Integer.parseInt(item.getText());
			updateShownPages();
		} catch (NumberFormatException nfe) {
			this.calculateNewSelectedPage(item.getText());
			updateShownPages();
		}
	}

}
