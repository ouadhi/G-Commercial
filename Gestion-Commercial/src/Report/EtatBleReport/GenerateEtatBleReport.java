/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatBleReport;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class GenerateEtatBleReport {

    List<Achat> achatDuJour = new ArrayList<>();

    public void getAchatDuJour(Date jour) {

        List<Achat> list = null;
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Achat").list();
            for (int i = 0; i < list.size(); i++) {
                if (jour.equals(list.get(i).getDateAcqt())) {
                    achatDuJour.add(list.get(i));
                }
            }

        } finally {
            session.close();
        }
    }

    public void generateReport(Date jour) throws IOException, JRException {
        OperationEtatBleReport operationEtatBleReport = new OperationEtatBleReport();
        getAchatDuJour(jour);

    }

}
