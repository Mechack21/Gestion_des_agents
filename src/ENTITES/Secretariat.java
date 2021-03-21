
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
public class Secretariat {
String Id_SECRETARIATGENERAL,Libelle,Id_MINISTERE;

    public String getId_SECRETARIATGENERAL() {
        return Id_SECRETARIATGENERAL;
    }

    public void setId_SECRETARIATGENERAL(String Id_SECRETARIATGENERAL) {
        this.Id_SECRETARIATGENERAL = Id_SECRETARIATGENERAL;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public String getId_MINISTERE() {
        return Id_MINISTERE;
    }

    public void setId_MINISTERE(String Id_MINISTERE) {
        this.Id_MINISTERE = Id_MINISTERE;
    }
Connexion mct = new Connexion();
 public void Ajouter() {
        try {
            String req = "insert into SECRETARIATGENERAL(Id_SECRETARIATGENERAL,Libelle,Id_MINISTERE) values('" + getId_SECRETARIATGENERAL() + "','" + getLibelle() + "','" + getId_MINISTERE() + "')";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur AJOUT: " + e.getMessage());
        }
    }

    public void Modifier() {
        try {
            String req = "update SECRETARIATGENERAL set Libelle='" + getLibelle() + "',Id_MINISTERE='" + getId_MINISTERE() + "' where Id_SECRETARIATGENERAL='" + getId_SECRETARIATGENERAL() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de MODIFICATION: " + e.getMessage());
        }
    }

    public void Supprimer() {
        try {
            String req = "delete from SECRETARIATGENERAL where Id_SECRETARIATGENERAL='" + getId_SECRETARIATGENERAL() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de SUPPRESSION: " + e.getMessage());
        }
    }

    public void remplir(JTable Grille,String req) {
        try {
//            String req = "select * from SECRETARIATGENERAL";
            int c = 0;
            mct.Selection(req);
            while (mct.reponse.next()) {
                c++;
            }
            Object data[][] = new Object[c][3];
            String titre[] = {"Identifiant du Secretariat", "Nom du Secretariat", "Ministeres"};
            mct.Selection(req);
            int i = 0;
            while (mct.reponse.next()) {
                data[i][0] = mct.reponse.getString(1);
                data[i][1] = mct.reponse.getString(2);
                data[i][2] = mct.reponse.getString(3);
                i++;
            }
            Grille.setModel(new DefaultTableModel(data,titre));
        } catch (Exception e) {
            System.out.println("Erreur du remplissage: "+ e.getMessage());
        }
    }
    public void Charger(JComboBox cmbx){
          try {
              String req="select Libelle from SECRETARIATGENERAL";
              cmbx.setModel(new DefaultComboBoxModel(new String[]{""}));
              mct.Selection(req);
              while (mct.reponse.next()) {                  
               cmbx.addItem(mct.reponse.getString(1));
              }
          } catch (Exception e) {
              System.out.println("Erreur de CHARGEMENT du combobox : "+e.getMessage());
          }
      }
    public String IDScr(String LibScr) {
        String id = "";
        try {
            String req = "select Id_SECRETARIATGENERAL from SECRETARIATGENERAL where Libelle='" + LibScr + "'";
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
