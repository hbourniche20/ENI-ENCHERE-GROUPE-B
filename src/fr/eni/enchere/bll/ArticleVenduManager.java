package fr.eni.enchere.bll;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleVenduDao;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.DaoFactory;
import fr.eni.enchere.dal.UtilisateurDao;

import fr.eni.enchere.exception.ArticleVenduException;
import fr.eni.enchere.exception.EnchereException;
import fr.eni.enchere.exception.UtilisateurNotFoundException;
import fr.eni.enchere.exception.WrongInputException;

public class ArticleVenduManager {
	
	private ArticleVenduDao dao;
	
	
	public ArticleVenduManager() {
		dao = DaoFactory.getArticleVenduDao();
		
	}

	public void enregistrerArticleVendu(ArticleVendu a) throws Exception{
		
		LocalDate date = LocalDate.now();
		
		if(a.getNomArticle().equals("")) {
			System.out.println("exception");
			throw new WrongInputException("Le nom de l'article est obligatoire");
		}
		
		if(a.getDescription().equals("")) {
			System.out.println("exception");
			throw new WrongInputException("La description de l'article est obligatoire");
		}
		
		if(a.getCategorieArticle()== null) {
			System.out.println("exception");
			throw new WrongInputException("La catégorie de l'article est obligatoire");
		}
		
		
		if(a.getDateDebutEncheres().isBefore(date)) {
			System.out.println("exception");
			throw new WrongInputException("La date de mise en vente doit être postérieur à aujourd'hui");
		}
		
		if(a.getDateFinEncheres().isBefore(date) || a.getDateFinEncheres().isBefore(a.getDateDebutEncheres())) {
			System.out.println("exception");
			throw new WrongInputException("Erreur lors de la saisie de la date de fin d'enchères! (Vérifiez qu'elle est bien postérieur à aujourd'hui ou à la date de mise en vente");
		}
		
		dao.addArticleVendu(a);
	}

	
	/**
	 * Recupérer un article à partir de son numéro
	 * 
	 * @param noArticle
	 * @return ArticleVendu
	 * @throws ArticleVenduException
	 */
	public ArticleVendu recupererArticleVendu(Integer noArticle) throws ArticleVenduException {
		return dao.selectArticleVenduById(noArticle);
	}
	
	/**
	 * Supprimer un article
	 * 
	 * @param vendeur
	 * @param noArticle
	 * @throws ArticleVenduException
	 */
	public void supprimerArticleVendu(Utilisateur vendeur, Integer noArticle) throws ArticleVenduException {
		LocalDate date = LocalDate.now();
		
		ArticleVendu article = recupererArticleVendu(noArticle);
		if(article != null) {
			if(vendeur.getNoUtilisateur() != article.getVendeur().getNoUtilisateur()) {
				throw new ArticleVenduException(ArticleVenduException.USER_FORBIDDEN_DELETE);
			}
			if(date.isEqual(article.getDateDebutEncheres()) || date.isAfter(article.getDateDebutEncheres())) {
				throw new ArticleVenduException(EnchereException.BEGIN_AUCTION);
			}
			
			// Suppression de l'article
			dao.delete(noArticle);
		} else {
			throw new ArticleVenduException();
		}
		
	}
}
