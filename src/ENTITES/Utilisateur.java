
package ENTITES;

import CONNEXIONS.Connexion;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author MECHACK
 */
public class Utilisateur {

    String Id_Utilisateur, MotDePasse;    
    
    public String getId_Utilisateur() {
        return Id_Utilisateur;
    }
    
    public void setId_Utilisateur(String Id_Utilisateur) {
        this.Id_Utilisateur = Id_Utilisateur;
    }
    
    public String getMotDePasse() {
        return MotDePasse;
    }
    
    public void setMotDePasse(String MotDePasse) {
        this.MotDePasse = MotDePasse;
    }
    Connexion mct = new Connexion();
    
    public void Ajouter() {
        try {
            String req = "insert into UTILISATEUR(Id_Utilisateur, MotDePasse) values ('" + getId_Utilisateur() + "','" + getMotDePasse() + "')";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur D'AJOUT : " + e.getMessage());
        }
    }
    
    public void Modifier() {
        try {
            String req = "update Utilisateur set Id_Utilisareur='" + getId_Utilisateur() + "',MotDePaase='" + getMotDePasse() + "' where MotDePasse='" + getMotDePasse() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur MODIFICATION: " + e.getMessage());
        }
    }
    
    public void Remplir(JTable Grille) {
        try {
            String req = "select * from Utilisateur";
            int c = 0;
            mct.Selection(req);
            while (mct.reponse.next()) {                
                c++;
            }
            Object data[][] = new Object[c][2];
            String titre[] = {"Nom d'Utilisateur", "Mot de passe"};
            mct.Selection(req);
            int i = 0;
            while (mct.reponse.next()) {                
                data[i][0] = mct.reponse.getString(1);
                data[i][1] = mct.reponse.getString(2);
                i++;
            }
        } catch (Exception e) {
        }
    }

  
}
