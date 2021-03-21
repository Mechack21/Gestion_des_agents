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
public class Territoire {

    String Id_TERRITOIRE, Libelle, ID_DISTRICT;

    public String getId_TERRITOIRE() {
        return Id_TERRITOIRE;
    }

    public void setId_TERRITOIRE(String Id_TERRITOIRE) {
        this.Id_TERRITOIRE = Id_TERRITOIRE;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public String getID_DISTRICT() {
        return ID_DISTRICT;
    }

    public void setID_DISTRICT(String ID_DISTRICT) {
        this.ID_DISTRICT = ID_DISTRICT;
    }
    Connexion mct = new Connexion();

    public void Ajouter() {
        try {
            String req = "insert into TERRITOIRE(Id_TERRITOIRE,Libelle,Id_DISTRICT) values('" + getId_TERRITOIRE() + "','" + getLibelle() + "','" + getID_DISTRICT() + "')";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur AJOUT: " + e.getMessage());
        }
    }

    public void Modifier() {
        try {
            String req = "update TERRITOIRE set Libelle='" + getLibelle() + "',Id_DISTRICT='" + getID_DISTRICT() + "' where Id_TERRITOIRE='" + getId_TERRITOIRE() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de MODIFICATION: " + e.getMessage());
        }
    }

    public void Supprimer() {
        try {
            String req = "delete from TERRITOIRE where Id_TERRITOIRE='" + getId_TERRITOIRE() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de SUPPRESSION: " + e.getMessage());
        }
    }

    public void remplir(JTable Grille,String req) {
        try {
//            String req = "select * from TERRITOIRE";
            int c = 0;
            mct.Selection(req);
            while (mct.reponse.next()) {
                c++;
            }
            Object data[][] = new Object[c][3];
            String titre[] = {"Identifiant du Territoire", "Nom du Territoire", "District"};
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
            String req = "select Libelle from TERRITOIRE";
            cmbx.setModel(new DefaultComboBoxModel(new String[]{""}));
            mct.Selection(req);
            while (mct.reponse.next()) {
                cmbx.addItem(mct.reponse.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Erreur de CHARGEMENT du combobox : " + e.getMessage());
        }
    }

    public String IDT(String LibT) {
        String id = "";
        try {
            String req = "select Id_TERRITOIRE from TERRITOIRE where Libelle='" + LibT + "'";
            mct.Selection(req);
            while (mct.reponse.next()) {
                id = mct.reponse.getString(1);
            }
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }
}
