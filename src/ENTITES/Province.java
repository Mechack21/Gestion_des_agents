
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
public class Province {
 String Id_PROVINCE,Libelle;

    public String getId_PROVINCE() {
        return Id_PROVINCE;
    }

    public void setId_PROVINCE(String Id_PROVINCE) {
        this.Id_PROVINCE = Id_PROVINCE;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }
 Connexion mct = new Connexion();
  public void Ajouter() {
        try {
            String req = "insert into PROVINCE(Id_PROVINCE,Libelle) values('" + getId_PROVINCE()+ "','" + getLibelle() + "')";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur AJOUT: " + e.getMessage());
        }
    }

    public void Modifier() {
        try {
            String req = "update PROVINCE set Libelle='" + getLibelle() + "' where Id_PROVINCE='"+getId_PROVINCE()+"'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de MODIFICATION: " + e.getMessage());
        }
    }

    public void Supprimer() {
        try {
            String req = "delete from PROVINCE where Id_PROVINCE='" +getId_PROVINCE() + "'";
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
            Object data[][] = new Object[c][2];
            String titre[] = {"Identifiant de la Province", "Nom de la Province"};
            mct.Selection(req);
            int i = 0;
            while (mct.reponse.next()) {
                data[i][0] = mct.reponse.getString(1);
                data[i][1] = mct.reponse.getString(2);
                i++;
            }
            Grille.setModel(new DefaultTableModel(data,titre));
        } catch (Exception e) {
            System.out.println("Erreur du remplissage: "+ e.getMessage());
        }
    }
    public void Charger(JComboBox cmbx){
          try {
              String req="select Libelle from PROVINCE";
              cmbx.setModel(new DefaultComboBoxModel(new String[]{""}));
              mct.Selection(req);
              while (mct.reponse.next()) {                  
               cmbx.addItem(mct.reponse.getString(1));
              }
          } catch (Exception e) {
              System.out.println("Erreur de CHARGEMENT du combobox : "+e.getMessage());
          }
      }
     public String IDP(String LibP) {
        String id = "";
        try {
            String req = "select Id_PROVINCE from PROVINCE where Libelle='" + LibP + "'";
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
