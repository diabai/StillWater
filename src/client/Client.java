package client;

/**
 * 
 * Client class represents a single client with clientId, last name, first name,
 * middle initial, street address, city, state and zip code
 * 
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class Client {

	private String mClientId;
	private String mFirstName;
	private String mLastName;
	private String mMiddleInitial;
	private String mStreetAddress;
	private String mCity;
	private String mState;
	private String mZipCode;

	/**
	 * Creates a client object with all his/her available information:
	 * 
	 * @param firstName
	 *            client's first name
	 * @param lastName
	 *            client's last name
	 * @param middleInitial
	 *            client's middle initial
	 * @param streetAddress
	 *            client's street address
	 * @param city
	 *            client's city
	 * @param state
	 *            client's state
	 * @param zipCode
	 *            client's zip code
	 */
	public Client(String lastName, String firstName, String middleInitial, String streetAddress, String city,
			String state, String zipCode) {

		this(lastName, firstName, streetAddress, city, state, zipCode);

		mMiddleInitial = middleInitial;

	}

	/**
	 * 
	 * Overloaded construction with only fields that are required.
	 * 
	 * @param firstName
	 *            client's first name
	 * @param lastName
	 *            client's last name
	 * @param streetAddress
	 *            client's street address
	 * @param city
	 *            client's city
	 * @param state
	 *            client's state
	 * @param zipCode
	 *            client's zip code
	 */
	public Client(String lastName, String firstName, String streetAddress, String city, String state, String zipCode) {

		setmFirstName(firstName);
		setmLastName(lastName);
		setmStreetAddress(streetAddress);
		setmCity(city);
		setmState(state);
		setmZipCode(zipCode);
	}

	public String getmFirstName() {
		return mFirstName;
	}

	public void setmFirstName(String firstName) {

		if (firstName == null) {
			throw new IllegalArgumentException("You must enter a first name !");
		}
		mFirstName = firstName;
	}

	public String getmLastName() {
		return mLastName;
	}

	public void setmLastName(String lastName) {

		if (lastName == null) {
			throw new IllegalArgumentException("You must enter a" + "last name !");
		}
		mLastName = lastName;
	}

	public String getmMiddleInitial() {
		return mMiddleInitial;
	}

	public void setmMiddleInitial(String middleInitial) {

		mMiddleInitial = middleInitial;
	}

	public String getmStreetAddress() {
		return mStreetAddress;
	}

	public void setmStreetAddress(String streetAddress) {
		if (streetAddress == null) {
			throw new IllegalArgumentException("You must enter a " + "address !");
		}
		mStreetAddress = streetAddress;
	}

	public String getmCity() {
		return mCity;
	}

	public void setmCity(String city) {

		if (city == null) {
			throw new IllegalArgumentException("You must enter a " + "city !");
		}
		mCity = city;
	}

	public String getmState() {
		return mState;
	}

	public void setmState(String state) {

		if (state == null) {
			throw new IllegalArgumentException("You must enter a " + "state !");
		}
		mState = state;
	}

	public String getmZipCode() {
		return mZipCode;
	}

	public void setmZipCode(String zipCode) {
		if (zipCode == null) {
			throw new IllegalArgumentException("You must enter a " + "zip code!");
		}
		mZipCode = zipCode;
	}

	public void setId(String clientId) {
		mClientId = clientId;
	}

	public String getId() {
		return mClientId;
	}

}
