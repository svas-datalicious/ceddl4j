package nl.mirabeau.ceddl4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import nl.mirabeau.ceddl4j.cart.Cart;
import nl.mirabeau.ceddl4j.component.Component;
import nl.mirabeau.ceddl4j.event.Event;
import nl.mirabeau.ceddl4j.page.Page;
import nl.mirabeau.ceddl4j.privacy.Privacy;
import nl.mirabeau.ceddl4j.product.Product;
import nl.mirabeau.ceddl4j.transaction.Transaction;
import nl.mirabeau.ceddl4j.user.User;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * CEDDL4J is a Java Library, with a fluent API, for creating a Data Layer
 * (JavaScript Object) that follows the Customer Experience Digital Data Layer
 * (CEDDL) specification.
 * <p>
 * The CEDDL specification "describes a
 * method for surfacing customer experience digital data on a web or other
 * digital resource as a JavaScript Object which can be used for communicating
 * this data to digital analytics and reporting servers."
 * <p>
 * The complete CEDDL specification can be found at <a
 * href="http://www.w3.org/2013/12/ceddl-201312.pdf"
 * >http://www.w3.org/2013/12/ceddl-201312.pdf</a>
 * <p>
 * Example Data Layer: (from the CEDDL specification)
 * 
 * <pre>
 * {@code
 * digitalData = {
 * 		pageInstanceID: "MyHomePage-Production",
 * 		page:{
 * 			pageInfo:{
 * 				pageID: "Home Page",
 * 				destinationURL: "http://mysite.com/index.html"
 * 			},
 * 			category:{
 * 				primaryCategory: "FAQ Pages",
 * 				subCategory1: "ProductInfo",
 * 				pageType: "FAQ"
 * 			},
 * 			attributes:{
 * 				country: "US",
 * 				language: "en-US"
 * 			}
 * 		 }
 * };
 * }
 * </pre>
 * <p>
 * The CEDDL4J code to generate this Data Layer:
 * <p>
 * 
 * <pre>
 * {@code
 * DigitalData ddb = DigitalData.create("MyHomePage-Production")
 * 		.page()
 * 		.pageInfo()
 * 		.pageID("Home Page")
 * 		.destinationURL("http://mysite.com/index.html")
 * 		.endPageInfo()
 * 		.addPrimaryCategory("FAQ Pages")
 * 		.addCategory("subCategory1", "ProductInfo")
 * 		.addCategory("pageType", "FAQ")
 * 		.addAttribute("country", "US")
 * 		.addAttribute("language", "en-US")
 * 		.endPage();
 * }
 * </pre>
 */
public class DigitalData {

	/**
	 * Constant for the version of CEDDL (1.0) implemented by this library.
	 */
	public static final String VERSION_1_0 = "1.0";

	/**
	 * Constant for the Default security category.
	 */
	public static final String DEFAULT_SECURITY = "Default";

	/**
	 * Name of the Root JavaScript Object: digitalData
	 */
	public static final String ROOT_JSO = "digitalData";

	@JsonProperty
	private String pageInstanceID;

	@JsonProperty
	private Page page;

	@JsonProperty
	private List<Product> product;

	@JsonProperty
	private Cart cart;

	@JsonProperty
	private Transaction transaction;

	@JsonProperty
	private List<Event> event;

	@JsonProperty
	private List<Component> component;

	@JsonProperty
	private List<User> user;

	@JsonProperty
	private Privacy privacy;

	@JsonProperty
	private String version;

	/**
	 * Constructs a DigitalData object.
	 */
	public DigitalData() {
		super();
	}

	/**
	 * Creates a new DigitalData object.
	 * 
	 * @return new DigitalData object
	 */
	public static DigitalData create() {
		return new DigitalData();
	}

	/**
	 * Creates a new DigitalData object and set the pageInstanceID.
	 * 
	 * @return new DigitalData object
	 */
	public static DigitalData create(final String pageInstanceID) {
		return new DigitalData().pageInstanceId(pageInstanceID);
	}

	/**
	 * Sets the Page Identifier Object.
	 * 
	 * @param pageInstanceID
	 *            A unique identifier for a page or other piece of content for
	 *            which data is being collected. This value SHOULD distinguish
	 *            among environments, such as whether this page is in
	 *            development, staging, or production.
	 * @return The current DigitalData object
	 */
	public DigitalData pageInstanceId(final String pageInstanceID) {
		this.pageInstanceID = pageInstanceID;
		return this;
	}

	/**
	 * Provides access to the Page object.
	 * 
	 * The Page object carries significant details about the page, and the most
	 * commonly used data elements are captured by the specification below.
	 * 
	 * @return Page object
	 */
	public Page page() {
		if (page == null) {
			page = new Page(this);
		}
		return page;
	}

	/**
	 * Sets the Page object.
	 * 
	 * The Page object carries significant details about the page, and the most
	 * commonly used data elements are captured by the specification below.
	 * 
	 * @param page
	 *            The Page Object to set.
	 * @return {@code this}
	 */
	public DigitalData setPage(final Page page) {
		this.page = page;
		return this;
	}

	/**
	 * Adds a new Product to the list of products.
	 * 
	 * The Product object carries details about a particular product with
	 * frequently used properties listed below. This is intended for data about
	 * products displayed on pages or other content. For products added to a
	 * shopping cart or ordered in a transaction, see the Cart and Transaction
	 * objects below.
	 * 
	 * @return a new Product object
	 */
	public Product addProduct() {
		if (product == null) {
			product = new ArrayList<Product>();
		}
		final Product newProduct = new Product(this);
		product.add(newProduct);

		return newProduct;
	}

	/**
	 * Adds a provided Product to the list of products.
	 * 
	 * The Product object carries details about a particular product with
	 * frequently used properties listed below. This is intended for data about
	 * products displayed on pages or other content. For products added to a
	 * shopping cart or ordered in a transaction, see the Cart and Transaction
	 * objects below.
	 * 
	 * @param newProduct
	 *            The Product Object to add to the list of products.
	 * @return {@code this}
	 */
	public DigitalData addProduct(final Product newProduct) {
		if (product == null) {
			product = new ArrayList<Product>();
		}
		product.add(newProduct);

		return this;
	}

	/**
	 * Provides access to the Cart object.
	 * 
	 * The Cart object carries details about a shopping cart or basket and the
	 * products that have been added to it. The Cart object is intended for a
	 * purchase that has not yet been completed. See the Transaction object
	 * below for completed orders.
	 * 
	 * @return Cart object
	 */
	public Cart cart() {
		if (cart == null) {
			cart = new Cart(this);
		}
		return cart;
	}

	/**
	 * Sets the Cart object.
	 * 
	 * The Cart object carries details about a shopping cart or basket and the
	 * products that have been added to it. The Cart object is intended for a
	 * purchase that has not yet been completed. See the Transaction object
	 * below for completed orders.
	 * 
	 * @param cart
	 *            The Cart Object to set.
	 * @return {@code this}
	 */
	public DigitalData setCart(final Cart cart) {
		this.cart = cart;
		return this;
	}

	/**
	 * Provides access to the Transaction object.
	 * 
	 * The Transaction object is similar to the Cart object, but represents a
	 * completed order. The Transaction object contains analogous sub-objects to
	 * the Cart object as well as additional subobjects specific to completed
	 * orders.
	 * 
	 * @return Transaction Object
	 */
	public Transaction transaction() {
		if (transaction == null) {
			transaction = new Transaction(this);
		}
		return transaction;
	}

	/**
	 * Sets the Transaction object
	 * 
	 * The Transaction object is similar to the Cart object, but represents a
	 * completed order. The Transaction object contains analogous sub-objects to
	 * the Cart object as well as additional subobjects specific to completed
	 * orders.
	 * 
	 * @param transaction
	 *            The Transaction Object to set.
	 * @return {@code this}
	 */
	public DigitalData setTransaction(final Transaction transaction) {
		this.transaction = transaction;
		return this;
	}

	/**
	 * Adds a new Event to the list of events that were measured.
	 * 
	 * The Event object collects information about an interaction event by the
	 * user. An event might be a button click, the addition of a portal widget,
	 * playing a video, adding a product to the shopping cart, etc. Any action
	 * on the page could be captured by an Event object.
	 * 
	 * @return a new Event object
	 */
	public Event addEvent() {
		if (event == null) {
			event = new ArrayList<Event>();
		}
		final Event newEvent = new Event(this);
		event.add(newEvent);
		return newEvent;
	}

	/**
	 * Adds a provided Event to the list of events that were measured.
	 * 
	 * The Event object collects information about an interaction event by the
	 * user. An event might be a button click, the addition of a portal widget,
	 * playing a video, adding a product to the shopping cart, etc. Any action
	 * on the page could be captured by an Event object.
	 * 
	 * @param newEvent
	 *            The Event Object to add to the list of events.
	 * @return {@code this}
	 */
	public DigitalData addEvent(final Event newEvent) {
		if (event == null) {
			event = new ArrayList<Event>();
		}
		event.add(newEvent);
		return this;
	}

	/**
	 * Adds a new Component to the list of page components.
	 * 
	 * The Component object is intended to capture information about a content
	 * component included as part of a page, such as a video. Interactions with
	 * the component  - such as playing the video - would be an Event, captured
	 * by the Event object above.
	 * 
	 * @return a new Component object
	 */
	public Component addComponent() {
		if (component == null) {
			component = new ArrayList<Component>();
		}
		final Component newComponent = new Component(this);
		component.add(newComponent);
		return newComponent;
	}

	/**
	 * Adds a provided Component to the list of page components.
	 * 
	 * The Component object is intended to capture information about a content
	 * component included as part of a page, such as a video. Interactions with
	 * the component - such as playing the video - would be an Event, captured
	 * by the Event object above.
	 * 
	 * @param newComponent
	 *            The Component Object to add to the list of page components.
	 * @return {@code this}
	 */
	public DigitalData addComponent(final Component newComponent) {
		if (component == null) {
			component = new ArrayList<Component>();
		}
		component.add(newComponent);
		return this;
	}

	/**
	 * Adds a new user to the list of list of users interacting with the page.
	 * 
	 * The User object captures the profile of a user who is interacting with
	 * the website.
	 * 
	 * (Although typically web data has a single user per recorded interaction,
	 * this object is an array and can capture multiple users.)
	 * 
	 * @return a new User object
	 */
	public User addUser() {
		if (user == null) {
			user = new ArrayList<User>();
		}
		final User newUser = new User(this);
		user.add(newUser);

		return newUser;
	}

	/**
	 * Adds a provided object user to the list of list of users interacting with
	 * the page.
	 * 
	 * The User object captures the profile of a user who is interacting with
	 * the website.
	 * 
	 * (Although typically web data has a single user per recorded interaction,
	 * this object is an array and can capture multiple users.)
	 * 
	 * @param newUser
	 *            The User Object to add to the list of users.
	 * @return {@code this}
	 */
	public DigitalData addUser(final User newUser) {
		if (user == null) {
			user = new ArrayList<User>();
		}
		user.add(newUser);
		return this;
	}

	/**
	 * Provides access to the Privacy object.
	 * 
	 * @return Privacy Object
	 */
	public Privacy privacy() {
		if (privacy == null) {
			privacy = new Privacy(this);
		}
		return privacy;
	}

	/**
	 * Sets the Privacy object.
	 * 
	 * @param privacy
	 *            The Privacy Object to set.
	 * @return {@code this}
	 */
	public DigitalData setPrivacy(final Privacy privacy) {
		this.privacy = privacy;
		return this;
	}

	/**
	 * Sets the version of the CEDDL specification used by the digitalData object
	 * to a custom value.
	 * 
	 * @param version
	 *            Custom version value
	 * @return The current DigitalData object
	 */
	public DigitalData version(final String version) {
		this.version = version;
		return this;
	}

	/**
	 * Sets the version of the CEDDL specification used by the digitalData object
	 * to "1.0".
	 * 
	 * @return The current DigitalData object
	 */
	public DigitalData version() {
		this.version = VERSION_1_0;
		return this;
	}

	/**
	 * Returns the DigitalData Object as a JSON String not including {@code digitalData =}.
	 * 
	 * @return String containing DigitalData in JSON format, not starting with
	 *         {@code digitalData =}
	 */
	@Override
	public String toString() {
		final ObjectMapper mapper = new ObjectMapper();

		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		mapper.setDateFormat(dateFormat);

		mapper.setSerializationInclusion(Include.NON_NULL);

		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the DigitalData Object as a JSON String including {@code digitalData =}.
	 * 
	 * @return String containing DigitalData in JSON format, starting with
	 *         {@code digitalData =}
	 */
	public String toStringWithRootObject() {

		return ROOT_JSO + " = " + this.toString();
	}
}
