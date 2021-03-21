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
public class District {

    String Id_DISTRICT, Libelle, Id_PROVINCE;

    public String getId_DISTRICT() {
        return Id_DISTRICT;
    }

    public void setId_DISTRICT(String Id_DISTRICT) {
        this.Id_DISTRICT = Id_DISTRICT;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public String getId_PROVINCE() {
        return Id_PROVINCE;
    }

    public void setId_PROVINCE(String Id_PROVINCE) {
        this.Id_PROVINCE = Id_PROVINCE;
    }
    Connexion mct = new Connexion();

    public void Ajouter() {
        try {
            String req = "insert into DISTRICT(Id_DISTRICT,Libelle,Id_PROVINCE) values('" + getId_DISTRICT() + "','" + getLibelle() + "','" + getId_PROVINCE() + "')";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur AJOUT: " + e.getMessage());
        }
    }

    public void Modifier() {
        try {
            String req = "update DISTRICT set Libelle='" + getLibelle() + "',Id_PROVINCE='" + getId_PROVINCE() + "' where Id_DISTRICT='" + getId_DISTRICT() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de MODIFICATION: " + e.getMessage());
        }
    }

    public void Supprimer() {
        try {
            String req = "delete from DISTRICT where Id_DISTRICT='" + getId_DISTRICT() + "'";
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
            String titre[] = {"Identifiant de la District", "Nom de la District", "Province"};
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
            String req = "select Libelle from DISTRICT";
            cmbx.setModel(new DefaultComboBoxModel(new String[]{""}));
            mct.Selection(req);
            while (mct.reponse.next()) {
                cmbx.addItem(mct.reponse.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Erreur de CHARGEMENT du combobox : " + e.getMessage());
        }
    }

    public String IDdis(String Libdis) {
        String id = "";
        try {
            String req = "select Id_DISTRICT from DISTRICT where Libelle='" + Libdis + "'";
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
