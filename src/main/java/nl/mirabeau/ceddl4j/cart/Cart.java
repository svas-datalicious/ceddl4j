package nl.mirabeau.ceddl4j.cart;

import java.util.ArrayList;
import java.util.List;

import nl.mirabeau.ceddl4j.DigitalData;
import nl.mirabeau.ceddl4j.shared.Attributes;
import nl.mirabeau.ceddl4j.shared.Item;
import nl.mirabeau.ceddl4j.shared.Price;
import nl.mirabeau.ceddl4j.shared.impl.AttributesImpl;
import nl.mirabeau.ceddl4j.shared.impl.ItemImpl;
import nl.mirabeau.ceddl4j.shared.impl.PriceImpl;

import com.google.gson.annotations.Expose;

/**
 * The Cart object carries details about a shopping cart or basket and the
 * products that have been added to it. The Cart object is intended for a
 * purchase that has not yet been completed. See the Transaction object for
 * completed orders.
 */
public class Cart {

	private DigitalData parent;

	@Expose
	private Object cartID;

	@Expose
	private PriceImpl<Cart> price;

	@Expose
	private AttributesImpl<Cart> attributes;

	@Expose
	private List<ItemImpl<Cart>> item;

	/**
	 * Constructor, does set the parent
	 * 
	 * @param parent
	 *            DigitalData parent Object
	 */
	public Cart(final DigitalData parent) {
		super();
		this.parent = parent;
	}

	/**
	 * Constructor, does not set the parent
	 */
	public Cart() {
		super();
	}

	/**
	 * Return to the parent DigitalData object
	 * 
	 * @return DigitalData parent object or {@code null} when not available
	 */
	public DigitalData endCart() {
		return parent;
	}

	/**
	 * Set the cartID
	 * 
	 * @param cartID
	 *            An identifier for a particular shopping cart.
	 * @return The current Cart object
	 */
	public Cart cartID(final Object cartID) {
		this.cartID = cartID;
		return this;
	}

	/**
	 * Provides access to the Price object.
	 * 
	 * This object provides details of the cart price. The basePrice SHOULD be
	 * the price of the items before applicable discounts, shipping charges, and
	 * tax. The cartTotal SHOULD be the total price inclusive of all discounts,
	 * charges, and tax.
	 * 
	 * @return The Price object for the current Cart
	 */
	public Price<Cart> price() {
		if (price == null) {
			price = new PriceImpl<Cart>(this);
		}
		return price;
	}

	/**
	 * Provides access to the Attributes object for this Cart.
	 * 
	 * This object provides extensibility to the cart as a whole. Any additional
	 * dimensions related to the cart can be provided. All names are optional
	 * and should fit the individual implementation needs in both naming and
	 * values passed.
	 * 
	 * @return Attributes object for this Cart
	 */
	public Attributes<Cart> attributes() {
		if (attributes == null) {
			attributes = new AttributesImpl<Cart>(this);
		}
		return attributes;
	}

	/**
	 * Directly add a new attribute to the Cart's attributes
	 * 
	 * @param name Name of the attribute
	 * @param value Value for the attribute
	 * @return The current Cart object
	 */
	public Cart addAttribuut(final String name, final Object value) {
		if (attributes == null) {
			attributes = new AttributesImpl<Cart>(this);
		}
		attributes.attribute(name, value);
		return this;
	}

	/**
	 * Add a new item to the list of items in the Cart.
	 * 
	 * @return a new Item object
	 */
	public Item<Cart> addItem() {
		if (item == null) {
			item = new ArrayList<ItemImpl<Cart>>();
		}
		final ItemImpl<Cart> newItem = new ItemImpl<Cart>(this);
		item.add(newItem);
		return newItem;
	}
}