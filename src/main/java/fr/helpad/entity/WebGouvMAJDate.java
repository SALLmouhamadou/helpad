package fr.helpad.entity;

import java.time.LocalDate;

public final class WebGouvMAJDate {
	public static LocalDate dateMiseAJour;
	public static String etat;
	public static Boolean isError;

	/**
	 * @return the isError
	 */
	public static boolean isError() {
		return isError;
	}

	/**
	 * @param isError the isError to set
	 */
	public static void setError(boolean isError) {
		WebGouvMAJDate.isError = isError;
	}

	/**
	 * @return the etat
	 */
	public static String getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public static void setEtat(String etat) {
		WebGouvMAJDate.etat = etat;
	}

	public static LocalDate getDateMiseAJour() {
		return dateMiseAJour;
	}

	public static void setDateMiseAJour(LocalDate dateMiseAJour) {
		WebGouvMAJDate.dateMiseAJour = dateMiseAJour;
	}

}
