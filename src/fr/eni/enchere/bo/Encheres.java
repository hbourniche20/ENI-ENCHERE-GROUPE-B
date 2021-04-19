package fr.eni.enchere.bo;

import java.util.Date;

public class Encheres {

	private int no_enchere;
	private Date date_enchere;
	private int montant_enchere;
	
	public Encheres() {
		
	}

	public Encheres(Date date_enchere, int montant_enchere) {
		
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
	}

	public Encheres(int no_enchere, Date date_enchere, int montant_enchere) {
		
		this.no_enchere = no_enchere;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
	}

	public int getNoEnchere() {
		return no_enchere;
	}

	public void setNoEnchere(int no_enchere) {
		this.no_enchere = no_enchere;
	}

	public Date getDateEnchere() {
		return date_enchere;
	}

	public void setDateEnchere(Date date_enchere) {
		this.date_enchere = date_enchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	
	
}