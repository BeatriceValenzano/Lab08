package it.polito.tdp.extflightdelays.model;

import java.util.Objects;

public class Volo {
	
	private Airport partenza;
	private Airport arrivo;
	private double distanzaMedia;
	
	public Volo(Airport partenza, Airport arrivo, double distanzaMedia) {
		super();
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.distanzaMedia = distanzaMedia;
	}

	public Airport getPartenza() {
		return partenza;
	}

	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}

	public Airport getArrivo() {
		return arrivo;
	}

	public void setArrivo(Airport arrivo) {
		this.arrivo = arrivo;
	}

	public double getDistanzaMedia() {
		return distanzaMedia;
	}

	public void setDistanzaMedia(double distanzaMedia) {
		this.distanzaMedia = distanzaMedia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(arrivo, distanzaMedia, partenza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Volo other = (Volo) obj;
		return Objects.equals(arrivo, other.arrivo)
				&& Double.doubleToLongBits(distanzaMedia) == Double.doubleToLongBits(other.distanzaMedia)
				&& Objects.equals(partenza, other.partenza);
	}

	
}
