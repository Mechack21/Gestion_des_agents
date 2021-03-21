package ENTITES;

import CONNEXIONS.Connexion;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MECHACK
 */
public class Bureau {

    String Id_BUREAU, Libelle, Id_Division;

    public String getId_BUREAU() {
        return Id_BUREAU;
    }

    public void setId_BUREAU(String Id_BUREAU) {
        this.Id_BUREAU = Id_BUREAU;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public String getId_Division() {
        return Id_Division;
    }

    public void setId_Division(String Id_Division) {
        this.Id_Division = Id_Division;
    }
    Connexion mct = new Connexion();

    public void Ajouter() {
        try {
            String req = "insert into Bureau(Id_Bureau,Libelle,Id_Division) values('" + getId_BUREAU() + "','" + getLibelle() + "','" + getId_Division() + "')";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur AJOUT: " + e.getMessage());
        }
    }

    public void Modifier() {
        try {
            String req = "update BUREAU set Libelle='" + getLibelle() + "',Id_DIVISION='" + getId_Division() + "' where Id_Bureau='" + getId_BUREAU() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de MODIFICATION: " + e.getMessage());
        }
    }

    public void Supprimer() {
        try {
            String req = "delete from Bureau where Id_BUREAU='" + getId_BUREAU() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de SUPPRESSION: " + e.getMessage());
        }
    }

    public void remplir(JTable Grille, String req) {
        try {

            int c = 0;
            mct.Selection(req);
            while (mct.reponse.next()) {
                c++;
            }
            Object data[][] = new Object[c][3];
            String titre[] = {"Identifiant du Bureau", "Nom du Bureau", "Division"};
            mct.Selection(req);
            int i = 0;
            while (mct.reponse.next()) {
                data[i][0] = mct.reponse.getString(1);
                data[i][1] = mct.reponse.getString(2);
                data[i][2] = mct.reponse.getString(3);
                i++;
            }
            Grille.setModel(new DefaultTableModel(data, titre));
        } catch (Exception e) {
            System.out.println("Erreur du remplissage: " + e.getMessage());
        }
    }

    public void Charger(JComboBox cmbx) {
        try {
            String req = "select Libelle from BUREAU";
            cmbx.setModel(new DefaultComboBoxModel(new String[]{""}));
            mct.Selection(req);
            while (mct.reponse.next()) {
                cmbx.addItem(mct.reponse.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Erreur de CHARGEMENT du combobox : " + e.getMessage());
        }
    }

    public String IDB(String LibB) {
        String id = "";
        try {
            String req = "select Id_BUREAU from Bureau where Libelle='" + LibB + "'";
            mct.Selection(req);
            while (mct.reponse.next()) {
                id = mct.reponse.getString(1);
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erreur d'identifiant" + e.getMessage());
        }

        return id;
    }
}
