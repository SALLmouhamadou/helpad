package fr.helpad.entity;

import java.time.LocalDate;

public final class WebGouvMAJDate {
	public static LocalDate dateMiseAJour;

	public static LocalDate getDateMiseAJour() {
		return dateMiseAJour;
	}

	public static void setDateMiseAJour(LocalDate dateMiseAJour) {
		WebGouvMAJDate.dateMiseAJour = dateMiseAJour;
	}

}
