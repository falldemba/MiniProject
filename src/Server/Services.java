/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Fall
 */
public class Services {

    public Services() {

    }
    server C = new server();
    private ResultSet rs;
    private int status = 0;
    private int MaxID = 0;
    private String total;
    private ResultSet sesulSet = null;
    private String NomMed=null;
    private String NomPat= null;

    public int Login(String login, String password) {
        try {

            String query = "SELECT * FROM Users WHERE Login='" + login + "' AND Password='" + password + "'";
            try {
                try (Statement st = C.connec().createStatement()) {
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        status = 1;
                    } else {
                        status = 2;
                    }
                }
                C.connec().close();
            } catch (HeadlessException | SQLException e) {
                // TODO: handle exception
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return status;
    }

    public int addPatient(Date date, String nom) {
        try {
            String query = "INSERT INTO patient(nom,date) VALUES (?,?)";
            PreparedStatement ps;
            ps = C.connec().prepareStatement(query);
            ps.setString(1, nom);
            ps.setObject(2, date);
            ps.executeUpdate();
            ps.close();
            C.connec().close();
            status = 1;
        } catch (SQLException e) {
            // TODO: handle exception
            status = 2;
        }
        return status;
    }

    public ResultSet showPatient() {

        try {
            String query = "SELECT Num_ss AS ID, nom AS NOM ,date AS DATE FROM patient";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return sesulSet;

    }

    public ResultSet showPatientID(int ID) {
        try {
            String query = "SELECT * FROM patient WHERE id= '" + ID + "'";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {

        }

        return sesulSet;
    }

    // Methodes Gestions des medecins
    public int addMedecin(String nom, Date date) {
        try {
            String query = "INSERT INTO medecin(nom,date) VALUES (?,?)";
            PreparedStatement ps;
            ps = C.connec().prepareStatement(query);
            ps.setString(1, nom);
            ps.setObject(2, date);
            ps.executeUpdate();
            ps.close();
            C.connec().close();
            status = 1;
        } catch (SQLException e) {
            // TODO: handle exception
            status = 2;
        }
        return status;
    }

    public ResultSet showMedecins() {

        try {
            String query = "SELECT * FROM medecin";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return sesulSet;

    }

    public String nombreTotalMedecin() {
        try {
            String query = "SELECT COUNT(Matricule) FROM medecin";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                total = sesulSet.getString(1);
            }
            // rs.close();
        } catch (SQLException e) {

        }
        return total;
    }

    public String nombreTotalPatient() {
        try {
            String query = "SELECT COUNT(Num_ss) AS NBRTOTAL FROM patient";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                total = sesulSet.getString(1);
            }
            // rs.close();
        } catch (SQLException e) {

        }
        return total;
    }

    public String nombreTotalMedicament() {
        try {
            String query = "SELECT COUNT(Code) AS NBRTOTAL FROM medicament";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                total = sesulSet.getString(1);
            }
            // rs.close();
        } catch (SQLException e) {

        }
        return total;
    }

    public String nombreTotalUtilisateur() {
        try {
            String query = "SELECT COUNT(Numero) AS NBRTOTAL FROM users";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                total = sesulSet.getString(1);
            }
            // rs.close();
        } catch (SQLException e) {

        }
        return total;
    }

    // Methodes Gestions des médicaments
    public int addMedicament(String Code, String Libelle, String Psologie, String Indications) {
        try {
            String query = "INSERT INTO medicament(Code,Libelle,Indications,Posologie) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = C.connec().prepareStatement(query);
            ps.setString(1, Code);
            ps.setString(2, Libelle);
            ps.setString(3, Indications);
            ps.setString(4, Psologie);
            ps.executeUpdate();
            ps.close();
            C.connec().close();
            status = 1;
        } catch (SQLException e) {
            // TODO: handle exception
            status = 2;
        }
        return status;
    }

    public ResultSet showMedicaments() {

        try {
            String query = "SELECT * FROM medicament";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return sesulSet;

    }

    public String nombreTotalPrescription() {
        try {
            String query = "SELECT COUNT(id_consultation) AS NBRTOTAL FROM prescription";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                total = sesulSet.getString(1);
            }
            // rs.close();
        } catch (SQLException e) {

        }
        return total;
    }

    public String nombreTotalConsultation() {
        try {
            String query = "SELECT COUNT(Numero) AS NBRTOTAL FROM consult";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                total = sesulSet.getString(1);
            }

            // rs.close();
        } catch (SQLException e) {

        }
        return total;
    }

    // Methodes ajout de consultations
    public int addConsultation(String Medecin, String Patient, Date date) {
        try {
            String MedecinID = null;
            String PatientID = null;
            String medecinID = "SELECT Matricule FROM medecin WHERE Nom='" + Medecin + "'";

            String patientID = "SELECT Num_ss FROM patient WHERE Nom ='" + Patient + "'";

            Statement stp = C.connec().createStatement();
            Statement stm = C.connec().createStatement();
            sesulSet = stp.executeQuery(medecinID);

            while (sesulSet.next()) {
                MedecinID = sesulSet.getString("Matricule");
            }

            sesulSet = stm.executeQuery(patientID);
            while (sesulSet.next()) {
                PatientID = sesulSet.getString("Num_ss");
            }

            String query = "INSERT INTO consult (ID_Medecin,ID_Patient,date) VALUES (?,?,?)";
            PreparedStatement ps;
            ps = C.connec().prepareStatement(query);
            ps.setString(1, MedecinID);
            ps.setString(2, PatientID);
            ps.setObject(3, date);
            ps.executeUpdate();
            ps.close();
            stp.close();
            stm.close();
            C.connec().close();
            status = 1;
        } catch (SQLException e) {
            // TODO: handle exception
            status = 2;
        }
        return status;
    }

    // Methodes Gestions des médicaments
    public int addUtisateur(String Username, String Password) {
        try {
            String query = "INSERT INTO users(Login,Password,Role) VALUES (?,?,?)";
            PreparedStatement ps;
            ps = C.connec().prepareStatement(query);
            ps.setString(1, Username);
            ps.setString(2, Password);
            ps.setInt(3, 0);
            ps.executeUpdate();
            ps.close();
            C.connec().close();
            status = 1;
        } catch (SQLException e) {
            // TODO: handle exception
            status = 2;
        }
        return status;
    }

    public ResultSet showUsers() {

        try {
            String query = "SELECT Numero AS ID,Login AS USERNAME FROM users";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return sesulSet;

    }

    public int addPrescription(int NumConsultation, String Medicament, String Quantite, String Jours) {
        try {
            String MedicamentID = null;

            String medicamentID = "SELECT Code FROM medicament WHERE Libelle='" + Medicament + "'";

            Statement stp = C.connec().createStatement();
            sesulSet = stp.executeQuery(medicamentID);

            while (sesulSet.next()) {
                MedicamentID = sesulSet.getString("Code");
            }
            System.out.println(MedicamentID);
            System.out.println(NumConsultation);
            System.out.println(Quantite);
            System.out.println(Jours);
            String query = "INSERT INTO prescription (id_consultation, id_medicament,quantite,jours) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = C.connec().prepareStatement(query);
            ps.setInt(1, NumConsultation);
            ps.setString(2, MedicamentID);
            ps.setString(3, Quantite);
            ps.setString(4, Jours);
            ps.executeUpdate();
            ps.close();
            stp.close();
            C.connec().close();
            status = 1;
        } catch (SQLException e) {
            // TODO: handle exception
            status = 2;
        }
        return status;
    }

    public int lastConsultation() {
        try {
            String query = "SELECT MAX(Numero) AS NUM FROM consult";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                MaxID = sesulSet.getInt("NUM");
            }
        } catch (SQLException e) {

        }
        return MaxID;
    }

    public ResultSet showPrescriptions(int code) {

        try {
            String query = "SELECT id_medicament AS CODE ,quantite AS QUANTITE,jours AS JOURS, Libelle AS LIBELLE, posologie AS PSOLOGIE FROM prescription p, medicament m WHERE p.id_medicament=m.code AND id_consultation ='"+code+"'";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return sesulSet;

    }
    
        public ResultSet showPrescription(String code) {

        try {
            String query = "SELECT id_medicament AS CODE ,quantite AS QUANTITE,jours AS JOURS, Libelle AS LIBELLE, posologie AS PSOLOGIE FROM prescription p, medicament m WHERE p.id_medicament=m.code AND id_consultation ='"+code+"'";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return sesulSet;

    }
        public String getMedecinPrescrit(String code) {

        try {
            String query = "SELECT m.nom AS NOM from medecin m , prescription p, consult c WHERE m.Matricule = c.ID_Medecin AND c.Numero=p.id_consultation AND c.Numero='"+code+"'";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                NomMed = sesulSet.getString("NOM");
            }
        } catch (SQLException e) {

        }
        return NomMed;
    }
                public String getPatientPrescrit(String code) {

        try {
            String query = "SELECT pa.nom AS NOM from patient pa , prescription p, consult c WHERE pa.Num_ss = c.ID_Patient AND c.Numero=p.id_consultation AND c.Numero='"+code+"'";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
            while (sesulSet.next()) {
                NomPat = sesulSet.getString("NOM");
            }
        } catch (SQLException e) {

        }
        return NomPat;
    }
    
        
        
        
    public ResultSet showAllPrescriptions() {

        try {
            String query = "SELECT id_medicament AS CODE ,quantite AS QUANTITE,jours AS JOURS, Libelle AS LIBELLE, posologie AS PSOLOGIE FROM prescription p, medicament m WHERE p.id_medicament=m.code";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return sesulSet;

    }
    
    
    public ResultSet showConsultations() {

        try {
            String query = "SELECT Numero AS CONSULTATION_ID, m.nom AS MEDECIN, p.nom AS PATIENT, c.date AS DATE FROM consult c, patient p, medecin m WHERE c.ID_Medecin=m.Matricule AND c.ID_Patient=p.Num_ss";
            Statement st = C.connec().createStatement();
            sesulSet = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return sesulSet;

    }
}
