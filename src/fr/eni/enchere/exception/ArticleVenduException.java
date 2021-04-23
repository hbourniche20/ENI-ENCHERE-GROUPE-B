package fr.eni.enchere.exception;

public class ArticleVenduException extends Exception {

	private static final long serialVersionUID = 1L;

	public ArticleVenduException(String message) {
		super(message);
	}
	
	public ArticleVenduException() {
		this("Impossible de récupérer le détail de l'article ");
	}
}