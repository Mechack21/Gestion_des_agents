
package ENTITES;

import CONNEXIONS.Connexion;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author MECHACK
 */
public class Ministere {
 String Id_MINISTERE,Libelle;

    public String getId_MINISTERE() {
        return Id_MINISTERE;
    }

    public void setId_MINISTERE(String Id_MINISTERE) {
        this.Id_MINISTERE = Id_MINISTERE;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }
   Connexion mct = new Connexion();
   public void Ajouter(){
       try {
           String req="insert into Ministere(Id_MINISTERE,Libelle) values ('"+getId_MINISTERE()+"','"+getLibelle()+"')";
           mct.mise_a_jour(req);
       } catch (Exception e) {
           System.out.println("Erreur d'AJOUT"+e.getMessage());
       }
   }
   
   public void Modifier(){
       try {
           String req="update MINISTERE set Libelle='"+getLibelle()+"' where Id_MINISTERE='"+getId_MINISTERE()+"'";
           mct.mise_a_jour(req);
       } catch (Exception e) {
        System.out.println("Erreur de MODIFICATION "+e.getMessage());
       }
   }
   
   public void Supprimer(){
       try {
           String req="delete from MINISTERE where Id_MINISTERE='"+getId_MINISTERE()+"'";
           mct.mise_a_jour(req);
       } catch (Exception e) {
       System.out.println("Erreur de SUPPRESSION "+e.getMessage());
       }
   }
   
      public void Remplir (JTable grille, String req){
        try {
//           String req="select * from MINISTERE";
           int c=0;
           mct.Selection(req);
            while (mct.reponse.next()) { 
            c++;
            }
             
           Object data [][]= new Object[c][2];
           String titre[]={"Identifiant Ministere","Nom du Ministere"};
           mct.Selection(req);
             int i=0;
            while (mct.reponse.next()) {                
                data[i][0]=mct.reponse.getString(1);
                data[i][1]=mct.reponse.getString(2);
                i++;
            }
            grille.setModel(new DefaultTableModel(data, titre));
       } catch (Exception e) {
            System.out.println("Erreur du remplissage: "+ e.getMessage());
       }
   }
      
      public void Charger(JComboBox cmbx){
          try {
              String req="select libelle from ministere";
              cmbx.setModel(new DefaultComboBoxModel(new String[]{""}));
              mct.Selection(req);
              while (mct.reponse.next()) {                  
               cmbx.addItem(mct.reponse.getString(1));
              }
          } catch (Exception e) {
              System.out.println("Erreur de CHARGEMENT du combobox : "+e.getMessage());
          }
      }
      public String IDMin(String LibMin) {
        String id = "";
        try {
            String req = "select Id_MINISTERE from MINISTERE where Libelle='" + LibMin + "'";
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
