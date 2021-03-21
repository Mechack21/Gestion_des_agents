
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
public class Direction {
   String Id_DIRECTION,Libelle,Id_SECRETARIATGENERAL; 

    public String getId_DIRECTION() {
        return Id_DIRECTION;
    }

    public void setId_DIRECTION(String Id_DIRECTION) {
        this.Id_DIRECTION = Id_DIRECTION;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public String getId_SECRETARIATGENERAL() {
        return Id_SECRETARIATGENERAL;
    }

    public void setId_SECRETARIATGENERAL(String Id_SECRETARIATGENERAL) {
        this.Id_SECRETARIATGENERAL = Id_SECRETARIATGENERAL;
    }
    Connexion mct =new Connexion();
     public void Ajouter() {
        try {
            String req = "insert into DIRECTION(Id_DIRECTION,Libelle,Id_SECRETARIATGENERAL) values('" + getId_DIRECTION()+ "','" + getLibelle() + "','" + getId_SECRETARIATGENERAL() + "')";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur AJOUT: " + e.getMessage());
        }
    }

    public void Modifier() {
        try {
            String req = "update DIRECTION set Libelle='" + getLibelle() + "',Id_SECRETARIATGENERAL='" + getId_SECRETARIATGENERAL() + "' where Id_DIRECTION='"+getId_DIRECTION()+"'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de MODIFICATION: " + e.getMessage());
        }
    }

    public void Supprimer() {
        try {
            String req = "delete from DIRECTION where Id_DIRECTION='" +getId_DIRECTION() + "'";
            mct.mise_a_jour(req);
        } catch (Exception e) {
            System.out.println("Erreur de SUPPRESSION: " + e.getMessage());
        }
    }

    public void remplir(JTable Grille,String req) {
        try {
//            String req = "select * from DIRECTION";
            int c = 0;
            mct.Selection(req);
            while (mct.reponse.next()) {
                c++;
            }
            Object data[][] = new Object[c][3];
            String titre[] = {"Identifiant de la Direction", "Nom de la Direction", "Secretariat"};
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
              String req="select Libelle from DIrection";
              cmbx.setModel(new DefaultComboBoxModel(new String[]{""}));
              mct.Selection(req);
              while (mct.reponse.next()) {                  
               cmbx.addItem(mct.reponse.getString(1));
              }
          } catch (Exception e) {
              System.out.println("Erreur de CHARGEMENT du combobox : "+e.getMessage());
          }
      }
       public String IDDrc(String LibDrc) {
        String id = "";
        try {
            String req = "select Id_DIRECTION from DIRECTION where Libelle='" + LibDrc + "'";
            mct.Selection(req);
            while (mct.reponse.next()) {                
             id= mct.reponse.getString(1);
            }
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }
}
