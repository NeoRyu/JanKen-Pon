import java.util.Random;
import java.util.Scanner;

public class ShiFuMi {
	
	// Variables d'environnement (scores)
	private static int atkScore = 0;
	private static int advScore = 0;
	
	// Instanciation de la saisie clavier
	private static Scanner reader = new Scanner(System.in);
	
	// Instanciation d'un nombre aleatoire
	private static Random aleat = new Random();
	
	public static void main(String[] args) {  
		System.out.print("         PIERRE - FEUILLE - CISEAUX\n");
		System.out.println("           Le tout sans IF !!! :D \n");	
		
		/* --- DEBUT DU JEU --- */
		
		boolean exit = false;
		while (!exit) 
		{		
			// SAISIE DE VOTRE MAIN
			  System.out.print("[ "+atkScore+" / "+advScore+" ] ");
			  PFC atk = saisie(); 			  
			  
			// GENERATION DE LA MAIN DE L'ORDI (GENERATION ALEATOIRE)
			  int ordi = (int)(Math.random()*(4-1));	  
			  PFC adv = (ordi == 1 ? PFC.P : (ordi == 2 ? PFC.F : PFC.C));		  
			  
			// AFFICHAGE DES MAINS (NOM : INITALE -> //COMPLET)
			  System.out.println("\nVOTRE MAIN : "+atk.getInitiale()) ; //atk.getChoix()
			  System.out.println("ADVERSAIRE : "+adv.getInitiale()) ; //adv.getChoix()
			 
			// AFFICHAGE FACON MARC
			  System.out.println("\nAFFICHAGE FACON MARC");
			  System.out.println("Comparaison int : "+atk.comparer(adv));
			  System.out.println("Comparaison bool : "+atk.comparerbin(adv));
			  
			// COMPARAISON DES MAINS ET AFFICHAGE DE VOTRE SCORE
			  System.out.println("\nAFFICHAGE FACON FRED");
			  int score = compareTo(atk.getChoix() , adv.getChoix());			  
			  /* TESTS : RESULTATS POSSIBLES
			   * P > F : -1
			   * P > C : 1
			   * P > P : 0
			   * F > C : -1
			   * F > P : 1
			   * F > F : 0
			   * C > P : -1
			   * C > F : 1
			   * C > c : 0
			   */
			  String Signe = (score == 0 ? " = " : (score == -1 ? " < " : " > " ) );
			  System.out.println(atk.getInitiale() + Signe + adv.getInitiale() + " : "+ score) ;
			  
			// SCORE TOTAL
			  atkScore = atkScore + score;
			  advScore = atkScore - score;
			  System.out.println("\n----------------------------------\n") ;
			  if ((atkScore >= 3) || (advScore >= 3)) exit = true;
		}
		
		/* --- RESULATS FINAUX --- */
		if (atkScore == 3)
			System.out.print("VOUS AVEZ GAGNÉ ! ") ;
		else
			System.out.print("VOUS AVEZ PERDU ! ") ;
		System.out.println(atkScore +" vs "+advScore) ;
	}
	
	
	
	private static PFC saisie() {		  
		  System.out.println("Votre choix... : P / F / C  ?");
		  String input = reader.nextLine();
		  PFC atk;
		  switch(input.toUpperCase()) {
		    case "0":
		    case "P":
			case "PIERRE":
				atk = PFC.P;
				break;
			case "1":
		    case "F":
			case "FEUILLE":
				atk = PFC.F;
				break;
			case "2":
		    case "C":
			case "CISEAUX":
				atk = PFC.C;
				break;
			default:
				atk = PFC.P;
				break;
		  }	
		return atk;
	}



	public enum PFC {		
		
		  // OBJETS ÉNUMÉRÉS
		  P("0","PIERRE","P"),
		  F("1","FEUILLE","F"),
		  C("2","CISEAUX","C");	
		
		  private String id = "";
		  private String choix = "";	
		  private String initiale = "";
		  
		  // CONSTRUCTEUR
		  PFC(String id, String choix, String initiale){
			  this.id = id;
			  this.choix = choix;
			  this.initiale = initiale;
		  }
		  
		  // METHODE D'APPEL
		  public String getId(){
			  return id;
		  }
		  public String getChoix(){
			  return choix;
		  }
		  // METHODE D'APPEL
		  public String getInitiale(){
			  return initiale;
		  }
		  
		  public int comparer(PFC adv) {		
				return this.getId().compareTo(adv.getId());
		  }
		  
		  public boolean comparerbin(PFC adv) {		
				return (this.getId().compareTo(adv.getId()) == 0 ? true : false);
		  }
		  
	}
	
	// Retourne 0 si atk et adv sont égaux, -1 si atk est battu par adv, 1 atk bas adv. 
	private static int compareTo(Object atk , Object adv) {
		int result;

		// COMPARAISON DE LA MAIN DU JOUEUR A CELUI DE L'ADVERSSAIRE
		switch(atk.toString().toUpperCase()) {
			case "PIERRE":
				result = (atk == adv ? 0 : (adv == PFC.F.getChoix().toString() ? -1 : 1 ) );
				break;
			case "FEUILLE":
				result = (atk == adv ? 0 : (adv == PFC.C.getChoix().toString() ? -1 : 1 ) );
				break;
			case "CISEAUX":
				result = (atk == adv ? 0 : (adv == PFC.P.getChoix().toString() ? -1 : 1 ) );
				break;
			default:
				result = 0;
				break;
		}		
		return result;
	}
}