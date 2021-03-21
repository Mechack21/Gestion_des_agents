 package ENTITES;

import CONNEXIONS.Connexion;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MECHACK
 */
public class Agent {
   String Id_AGENT, Nom, Postnom, Prenom, Sexe, Id_GRADE, Id_BUREAU, Id_TERRITOIRE;

    public String getId_AGENT() {
        return Id_AGENT;
    }

    public void setId_AGENT(String Id_AGENT) {
        this.Id_AGENT = Id_AGENT;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPostnom() {
        return Postnom;
    }

    public void setPostnom(String Postnom) {
        this.Postnom = Postnom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public String getId_GRADE() {
        return Id_GRADE;
    }

    public void setId_GRADE(String Id_GRADE) {
        this.Id_GRADE = Id_GRADE;
    }

    public String getId_BUREAU() {
        return Id_BUREAU;
    }

    public void setId_BUREAU(String Id_BUREAU) {
        this.Id_BUREAU = Id_BUREAU;
    }

    public String getId_TERRITOIRE() {
        return Id_TERRITOIRE;
    }

    public void setId_TERRITOIRE(String Id_TERRITOIRE) {
        this.Id_TERRITOIRE = Id_TERRITOIRE;
    }
   
   Connexion mct = new Connexion();
   
   public void Remplir (JTable grille, String req){
        try {
          
           int c=0;
           mct.Selection(req);
            while (mct.reponse.next()) { 
            c++;
            }
             
           Object data [][]= new Object[c][8];
           String titre[]={"Matricule","Nom","Postnom","Prenom","Sexe",
                            "Grade","Bureau","Territoire"};
           mct.Selection(req);
             int i=0;
            while (mct.reponse.next()) {                
              
                data[i][0]=mct.reponse.getString(1);
                data[i][1]=mct.reponse.getString(2);
                data[i][2]=mct.reponse.getString(3);
                data[i][3]=mct.reponse.getString(4);
                data[i][4]=mct.reponse.getString(5);
                data[i][5]=mct.reponse.getString(6);
                data[i][6]=mct.reponse.getString(7);
                data[i][7]=mct.reponse.getString(8);
                i++;
            }
            
            grille.setModel(new DefaultTableModel(data, titre));
       } catch (Exception e) {
            System.out.println("Erreur du remplissage: "+ e.getMessage());
       }
   }
   
   public void Ajouter (){
       try {
           String req="insert into agent (Id_AGENT,Nom,Postnom,Prenom,Sexe,Id_GRADE,Id_BUREAU,Id_TERRITOIRE)values('"+getId_AGENT()+"','"+getNom()+"','"+getPostnom()+"','"+getPrenom()+"','"+getSexe()+"','"+getId_GRADE()+"','"+getId_BUREAU()+"','"+getId_TERRITOIRE()+"')";
           mct.mise_a_jour(req);
       } catch (Exception e) {
           System.out.println("Erreur d'AJOUT: " +e.getMessage());
       }
   }
   
   public void Modifier(){
       try {
        String req="update agent set Nom='"+getNom()+"',Postnom='"+getPostnom()+"',Prenom='"+getPrenom()+"',sexe='"+getSexe()+"',Id_GRADE='"+getId_GRADE()+"',Id_BUREAU='"+getId_BUREAU()+"',Id_TERRITOIRE='"+getId_TERRITOIRE()+"' where Id_AGENT='"+getId_AGENT()+"'"; 
        mct.mise_a_jour(req);
       } catch (Exception e) {
           System.out.println("Erreur de MODIFICATION: " +e.getMessage());
       }
   }
   
   public void Supprimer(){
       try {
         String req="delete from agent where Id_AGENT='"+getId_AGENT()+"'"; 
         mct.mise_a_jour(req);
       } catch (Exception e) {
        System.out.println("Erreur de SUPPRESSION: " +e.getMessage());
       }
   }
}
