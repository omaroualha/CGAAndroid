package cga.esprit.cgaandroid;


import java.io.Serializable;

/**
 * Entity implementation class for Entity: Claim
 *
 */
public class Claim implements Serializable {


	private static final long serialVersionUID = 1999999L;


	private int id;

	private String accidentDate;
	private String accidentHour;
	private String localisation;
	private boolean injured;
	private boolean vehiclesDamage;
	private boolean otherObjectDamage;
	private String  namesAddressesPhonesWitnesses;
	private String sketchOfTheAccident;
	private String insured;

	public Claim(String accidentDate, String accidentHour, String localisation, boolean injured, boolean vehiclesDamage,
			boolean otherObjectDamage, String namesAddressesPhonesWitnesses, String sketchOfTheAccident,
			String insured) {
		super();
		this.accidentDate = accidentDate;
		this.accidentHour = accidentHour;
		this.localisation = localisation;
		this.injured = injured;
		this.vehiclesDamage = vehiclesDamage;
		this.otherObjectDamage = otherObjectDamage;
		this.namesAddressesPhonesWitnesses = namesAddressesPhonesWitnesses;
		this.sketchOfTheAccident = sketchOfTheAccident;
		this.insured = insured;
	}

	public Claim(int id, String accidentDate, String accidentHour, String localisation, boolean injured,
			boolean vehiclesDamage, boolean otherObjectDamage, String namesAddressesPhonesWitnesses,
			String sketchOfTheAccident, String insured) {
		super();
		this.id = id;
		this.accidentDate = accidentDate;
		this.accidentHour = accidentHour;
		this.localisation = localisation;
		this.injured = injured;
		this.vehiclesDamage = vehiclesDamage;
		this.otherObjectDamage = otherObjectDamage;
		this.namesAddressesPhonesWitnesses = namesAddressesPhonesWitnesses;
		this.sketchOfTheAccident = sketchOfTheAccident;
		this.insured = insured;
	}


	public Claim(String accidentDate, String accidentHour, String localisation, boolean injured, boolean vehiclesDamage,
			String insured) {
		super();
		this.accidentDate = accidentDate;
		this.accidentHour = accidentHour;
		this.localisation = localisation;
		this.injured = injured;
		this.vehiclesDamage = vehiclesDamage;
		this.insured = insured;
	}

	public Claim(int id, String accidentDate, String accidentHour, String localisation, boolean injured,
			boolean vehiclesDamage, boolean otherObjectDamage, String namesAddressesPhonesWitnesses,
			String sketchOfTheAccident) {
		super();
		this.id = id;
		this.accidentDate = accidentDate;
		this.accidentHour = accidentHour;
		this.localisation = localisation;
		this.injured = injured;
		this.vehiclesDamage = vehiclesDamage;
		this.otherObjectDamage = otherObjectDamage;
		this.namesAddressesPhonesWitnesses = namesAddressesPhonesWitnesses;
		this.sketchOfTheAccident = sketchOfTheAccident;
	}

	public Claim() {
		super();
	}

	public String getInsured() {
		return insured;
	}

	public void setInsured(String insured) {
		this.insured = insured;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getAccidentHour() {
		return accidentHour;
	}

	public void setAccidentHour(String accidentHour) {
		this.accidentHour = accidentHour;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public boolean isInjured() {
		return injured;
	}

	public void setInjured(boolean injured) {
		this.injured = injured;
	}

	public boolean isVehiclesDamage() {
		return vehiclesDamage;
	}

	public void setVehiclesDamage(boolean vehiclesDamage) {
		this.vehiclesDamage = vehiclesDamage;
	}

	public boolean isOtherObjectDamage() {
		return otherObjectDamage;
	}

	public void setOtherObjectDamage(boolean otherObjectDamage) {
		this.otherObjectDamage = otherObjectDamage;
	}

	public String getNamesAddressesPhonesWitnesses() {
		return namesAddressesPhonesWitnesses;
	}

	public void setNamesAddressesPhonesWitnesses(String namesAddressesPhonesWitnesses) {
		this.namesAddressesPhonesWitnesses = namesAddressesPhonesWitnesses;
	}



	public String getSketchOfTheAccident() {
		return sketchOfTheAccident;
	}

	public void setSketchOfTheAccident(String sketchOfTheAccident) {
		this.sketchOfTheAccident = sketchOfTheAccident;
	}

	@Override
	public String toString() {
		return "Claim{" +
				"id=" + id +
				", accidentDate=" + accidentDate +
				", accidentHour='" + accidentHour + '\'' +
				", localisation='" + localisation + '\'' +
				", injured=" + injured +
				", vehiclesDamage=" + vehiclesDamage +
				", otherObjectDamage=" + otherObjectDamage +
				", namesAddressesPhonesWitnesses='" + namesAddressesPhonesWitnesses + '\'' +
				", sketchOfTheAccident='" + sketchOfTheAccident + '\'' +
				", insured='" + insured + '\'' +
				'}';
	}
}
