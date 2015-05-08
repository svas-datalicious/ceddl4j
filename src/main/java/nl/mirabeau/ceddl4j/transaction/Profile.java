package nl.mirabeau.ceddl4j.transaction;

import nl.mirabeau.ceddl4j.shared.Address;
import nl.mirabeau.ceddl4j.shared.ProfileInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A profile for information about the purchaser, typically associated with a
 * registered user.
 */
public class Profile {

	private final Transaction parent;

	@JsonProperty
	private ProfileInfo<Profile> profileInfo;

	@JsonProperty
	private Address<Profile> address;

	@JsonProperty
	private Address<Profile> shippingAddress;

	/**
	 * Constructor, sets the parent
	 * 
	 * @param parent
	 */
	public Profile(final Transaction parent) {
		super();
		this.parent = parent;
	}

	/**
	 * Return to the parent Transaction object
	 * 
	 * @return Transaction parent object or {@code null} when not available
	 */
	public Transaction endProfile() {
		return parent;
	}

	/**
	 * Provides access to the ProfileInfo object
	 * 
	 * An extensible object for providing information about the purchaser.
	 * 
	 * @return the ProfileInfo object for this Profile
	 */
	public ProfileInfo<Profile> profileInfo() {
		if (profileInfo == null) {
			profileInfo = new ProfileInfo<Profile>(this);
		}
		return profileInfo;
	}

	/**
	 * Provides access to the Address object.
	 * 
	 * An extensible object for providing (billing) address information for the
	 * purchaser.
	 * 
	 * @return The Address object for this Profile
	 */
	public Address<Profile> address() {
		if (address == null) {
			address = new Address<Profile>(this);
		}
		return address;
	}

	/**
	 * Provides access to the Shipping Address object.
	 * 
	 * An extensible object for providing shipping address information for the
	 * purchaser.
	 * 
	 * @return The Shipping Address for this Profile
	 */
	public Address<Profile> shippingAddress() {
		if (shippingAddress == null) {
			shippingAddress = new Address<Profile>(this);
		}
		return shippingAddress;
	}
}
