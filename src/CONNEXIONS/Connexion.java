package CONNEXIONS;

//import java.awt.image.ImageProducer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.Map;
//import java.util.*;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MECHACK
 */
public class Connexion {
   private Connection meconnecter; 

    public Connection getMeconnecter() {
        return meconnecter;
    }
    private Statement Dbd;
    public ResultSet reponse; 

    public String getChemin() {
        return chemin;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    
    String chemin = "jdbc:sqlserver://PEDRO\\MUTOKASERVER:1433;databaseName=AgentManagement";
    String utilisateur="sa";
    String motDePasse="123456";
    
    public Connexion(){
       try {
           meconnecter= DriverManager.getConnection(chemin, utilisateur, motDePasse);
       } catch (SQLException ex) {
           Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public void mise_a_jour(String requete){
       try { 
           Dbd=meconnecter.createStatement();
          int test = Dbd.executeUpdate(requete);
           if (test==1) {
               System.out.println("Operation reussie"); 
           }else if (test==0) {
               System.out.println("Echec operation");
           }
       } catch (SQLException ex) {
           Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void Selection (String requete1){
       try {
           Dbd=meconnecter.createStatement(); 
           reponse=Dbd.executeQuery(requete1);
       } catch (SQLException ex) {
           Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
 
    

    
}