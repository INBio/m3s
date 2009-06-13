/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata;

import java.util.List;

import org.inbio.m3s.gwt.client.config.ClientProperties;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPCAsync;
import org.inbio.m3s.gwt.client.widgets.metadata.listener.CategoryAndTypeListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget the manages the relation beetwen categories and types of multimedia.
 * Has a functionality for the use case when is used for modification
 * 
 * @author jgutierrez
 * 
 */
public class CategoryAndTypeSelector extends Composite implements
		ChangeListener {

	private CategoryAndTypeListener listener;

	private MetadataRPCAsync rpc;

	private HorizontalPanel main = new HorizontalPanel();

	private ListBox categories;

	private ListBox types;

	private static int GET_MEDIA_CATEGORIES = 0;

	private static int GET_MEDIA_TYPES = 1;

	// ..:wired variables:..
	// images
	// private String actualCategory = "Imágenes";
	private String actualCategory = ClientProperties.DEFAULT_MEDIA_CATEGORY;

	// ->digital camara
	// private String actualType = "Cámara Digital";
	private String actualType = ClientProperties.DEFAULT_MEDIA_TYPE;

	/**
	 * Class Constructor used when the widget is in "insert info" mode
	 * 
	 */
	public CategoryAndTypeSelector(CategoryAndTypeListener listener) {
		this.listener = listener;
		initCategoryAndTypeSelector();
	}

	/**
	 * Class constructor used when the widget is in "edit info" mode
	 * 
	 * @param mediaCategoryName
	 *            literal value of the category to be shown
	 * @param mediaTypeName
	 *            literal value of the media type to be shown
	 */
	public CategoryAndTypeSelector(String mediaCategoryName,
			String mediaTypeName, CategoryAndTypeListener listener) {
		this.actualCategory = mediaCategoryName;
		this.actualType = mediaTypeName;
		this.listener = listener;
		initCategoryAndTypeSelector();
	}

	/**
	 * Inits the widget attributes, this is the real constructor of the widget.
	 * 
	 * ACording to the predefinedCategory and predefinedType variables the
	 * ListBoxes are set to show that info. The categories ListBox is set with a
	 * changeListener in order to update the mediaTypes to show the ones of the
	 * selected category.
	 * 
	 */
	private void initCategoryAndTypeSelector() {
		this.initRPC();

		// categories stuff
		categories = new ListBox();
		categories.addChangeListener(this);
		rpc.getMediaCategories(new AsyncCallback() {

			public void onFailure(Throwable caught) {
				RPCFailureManager(GET_MEDIA_CATEGORIES, caught);
			}

			public void onSuccess(Object result) {
				RPCSuccessManager(GET_MEDIA_CATEGORIES, result);
			}

		});

		// types stuff
		types = new ListBox();

		// sets both in the panel and ready!
		main.setSpacing(10);
		main.add(categories);
		main.add(types);
		initWidget(main);
	}

	/**
	 * Sets a list of textual mediaCategories
	 * 
	 * @param mediaCategories
	 *            is a list with the literal values to be shown
	 */
	private void setMediaCategoriesList(List mediaCategories) {
		categories.clear();
		System.out.println("setMediaCategoriesList con size "
				+ mediaCategories.size());

		for (int i = 0; i < mediaCategories.size(); i++) {
			categories.addItem((String) mediaCategories.get(i));
		}

	}

	/**
	 * This method asumes the setMediaCategoriesList has been already called and
	 * the idea of this method is to use the setSelected method of the listBox,
	 * but using as parameter the DBId of one category previously inserted in
	 * the listbox
	 * 
	 * @param categoryName
	 */
	private void setMediaCategory(String categoryName) {
		String pivoteValue;

		for (int i = 0; i < categories.getItemCount(); i++) {
			pivoteValue = (String) categories.getValue(i);

			// if (pivoteValue.compareTo(categoryName) == 0) {
			if (pivoteValue.equals(categoryName)) {
				categories.setSelectedIndex(i);
				break;
			}
		}
	}

	/**
	 * Return the selected CategoryName
	 * 
	 * @return
	 */
	public String getMediaCategory() {
		return categories.getItemText(categories.getSelectedIndex());
	}

	/**
	 * This method helps sets the listBox textual values.
	 * 
	 * @param mediaTypes
	 *            list of textual values
	 */
	private void setMediaTypesList(List mediaTypes) {
		types.clear();

		for (int i = 0; i < mediaTypes.size(); i++) {
			types.addItem((String) mediaTypes.get(i));
		}
	}

	/**
	 * This method asumes the setMediaTypesList has been already called and the
	 * idea of this method is to use the setSelected method of the listBox, but
	 * using as parameter the Textual value of one type previously inserted in
	 * the listbox.
	 * 
	 * @param mediaTypeName
	 */
	private void setMediaType(String mediaTypeName) {
		String pivoteValue;

		for (int i = 0; i < types.getItemCount(); i++) {
			pivoteValue = (String) types.getValue(i);
			// if (pivoteValue.compareTo(mediaTypeName) == 0) {
			if (pivoteValue.equals(mediaTypeName)) {
				types.setSelectedIndex(i);
				break;
			}
		}
	}

	/**
	 * Returns the type name selected
	 * 
	 * @return database Id of the media type selected
	 */
	public String getMediaType() {
		return types.getItemText(types.getSelectedIndex());
	}

	/**
	 * Cause the mediatypes had a dependency on the categories the only way to
	 * set the values are doing that for both at the same time.
	 * 
	 * @param mediaCategory
	 * @param mediaType
	 */
	public void setMediaCategoryAndType(String mediaCategory, String mediaType) {
		this.actualCategory = mediaCategory;
		this.actualType = mediaType;

		rpc.getMediaCategories(new AsyncCallback() {

			public void onFailure(Throwable caught) {
				RPCFailureManager(GET_MEDIA_CATEGORIES, caught);
			}

			public void onSuccess(Object result) {
				RPCSuccessManager(GET_MEDIA_CATEGORIES, result);
			}

		});

	}

	/***************************************************************************
	 * RPC methods
	 **************************************************************************/
	/**
	 * Called by the the method that receives the asyncCallback result
	 * 
	 * @param caught
	 */
	private void RPCFailureManager(int methodId, Throwable caught) {

		if (methodId == GET_MEDIA_CATEGORIES) {
			System.out.println("no se pueden obtener las categorias");
		} else if (methodId == GET_MEDIA_TYPES) {
			System.out.println("no se pueden obtener los tipos");
		}

		try {
			throw caught;
		} catch (InvocationException e) {
			System.out
					.println("InvocationException @ MediaCategoryAndTypeSelector INIT");
			System.out.println(e.getMessage());
			System.out
					.println("InvocationException @ MediaCategoryAndTypeSelector END");
			Window.alert("Error conectando con el servidor");
		} catch (IllegalArgumentException e) {
			System.out
					.println("Uno de los argumentos introducidos no existe INIT");
			System.out.println(e.getMessage());
			System.out
					.println("Uno de los argumentos introducidos no existe END");
		} catch (Throwable e) {
			System.out.println("RPC error @ MediaCategoryAndTypeSelector INIT");
			System.out.println(e.getMessage());
			System.out.println("RPC error @ MediaCategoryAndTypeSelector END");
		}

	}

	/**
	 * Called by the the method that receives the asyncCallback result
	 */
	private void RPCSuccessManager(int methodId, Object fromServer) {

		if (methodId == GET_MEDIA_CATEGORIES) {
			setMediaCategoriesList((List) fromServer);

			setMediaCategory(actualCategory);

			rpc.getMediaTypes(getMediaCategory(), new AsyncCallback() {
				public void onFailure(Throwable caught) {
					RPCFailureManager(GET_MEDIA_TYPES, caught);
				}

				public void onSuccess(Object result) {
					RPCSuccessManager(GET_MEDIA_TYPES, result);
				}
			});

		} else if (methodId == GET_MEDIA_TYPES) {

			setMediaTypesList((List) fromServer);
			if (getMediaCategory().compareTo(actualCategory) == 0) {
				setMediaType(actualType);
			}
		}

	}

	/**
	 * Init the RPC that all the class use
	 * 
	 */
	private void initRPC() {
		// (1) Initialize the RPC service.
		rpc = (MetadataRPCAsync) GWT.create(MetadataRPC.class);

		// (2) Specify the URL at which our service implementation is running.
		// Note that the target URL must reside on the same domain and port from
		// which the host page was served.
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "metadataRPC";
		endpoint.setServiceEntryPoint(moduleRelativeURL);
	}

	/**
	 * Fired when the categories or types ListBox change its status
	 */
	public void onChange(Widget sender) {

		if (categories.equals((ListBox) sender)) {
			System.out.println("must be shown " + this.getMediaCategory()
					+ " types");
			ListBox categories = (ListBox) sender;

			actualCategory = categories.getItemText(categories
					.getSelectedIndex());

			rpc.getMediaTypes(getMediaCategory(), new AsyncCallback() {
				public void onFailure(Throwable caught) {
					RPCFailureManager(GET_MEDIA_TYPES, caught);
				}

				public void onSuccess(Object result) {
					RPCSuccessManager(GET_MEDIA_TYPES, result);
				}
			});
		} else if (types.equals((ListBox) sender)) {
			listener.onTypeChange(getMediaType());
		}
	}
}
