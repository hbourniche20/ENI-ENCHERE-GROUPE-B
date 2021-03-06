package fr.eni.enchere.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ConnexionManager;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exception.ConnexionException;

/**
 * Servlet implementation class ConnexionTestServlet
 */
@WebServlet("/servlet/fr.eni.enchere.test.ConnexionTestServlet")
public class ConnexionTestServlet extends TestServlet {
	private static final long serialVersionUID = 1L;

	private static ConnexionManager manager;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		manager = new ConnexionManager();
		printFirstMessage();		
		test1EmailSuccess();
		test2PseudoSuccess();
		test3EmailFail();
		test4PseudoFail();
		test5PasswordFail();
		printLastMessage(response);
	}
	
	private static void test1EmailSuccess() {
		printNewTest("Connection avec email");
		Utilisateur user = null;
		try {
			user = manager.authentification("dupond.jean@gmail.com", "azertyuiop");
		} catch (ConnexionException e) {
			printTestFail(e.getMessage());
			return;
		}
		if(user == null) {
			printTestFail("Utilisateur null");
		} else {
			System.out.println("Test réussi: le compte est connecté avec l'email.");
		}
	}

	private static void test2PseudoSuccess() {
		printNewTest("Connection avec le pseudo");
		Utilisateur user = null;
		try {
			user = manager.authentification("Dupond85", "azertyuiop");
		} catch (ConnexionException e) {
			printTestFail(e.getMessage());
			return;
		}
		if(user == null) {
			printTestFail("Utilisateur null");
		} else {
			System.out.println("Test réussi: le compte est connecté avec le pseudo.");
		}
	}
	
	private static void test3EmailFail() {
		printNewTest("Connection avec email");
		try {
			manager.authentification("dupont.jean@gmail.com", "azertyuiop");
		} catch (ConnexionException e) {
			System.out.println("Test réussi: l'email n'existe pas dans la db.");
			return;
		}
		printTestFail("Compte avec dupont.jean@gmail.com existant");
	}

	private static void test4PseudoFail() {
		printNewTest("Connection avec le pseudo");
		try {
			manager.authentification("Dupont85", "azertyuiop");
		} catch (ConnexionException e) {
			System.out.println("Test réussi: le pseudo n'existe pas dans la db.");
			return;
		}
		printTestFail("Compte avec Dupont85 existant");
	}
	
	private static void test5PasswordFail() {
		printNewTest("Connection avec email");
		try {
			manager.authentification("dupond.jean@gmail.com", "azertyuio");
		} catch (ConnexionException e) {
			System.out.println("Test réussi: mauvais mot de passe entré.");
			return;
		}
		printTestFail("Mot de passe azertyuio pour le compte dupond.jean@gmail.com existant");
	}

}
