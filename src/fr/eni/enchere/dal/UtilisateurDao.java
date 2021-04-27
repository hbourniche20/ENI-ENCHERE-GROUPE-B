package fr.eni.enchere.dal;


import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exception.EmailNotUniqueException;
import fr.eni.enchere.exception.PseudoNotUniqueException;
import fr.eni.enchere.exception.UtilisateurNotFoundException;

public interface UtilisateurDao {

	void saveUtilisateur(Utilisateur u) throws PseudoNotUniqueException, EmailNotUniqueException;
	
	Utilisateur getUtilisateur(String pseudo) throws UtilisateurNotFoundException;
	
	void deleteUtilisateur(Utilisateur u)  throws UtilisateurNotFoundException;
}
