/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatExpeditionReport;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.Facture_ProduitQueries;
import com.gestionCommerciale.Models.PaymentQueries;
import com.gestionCommerciale.Models.SessionsGenerator;

import net.sf.jasperreports.engine.JRException;

/**
 * @author Hicham
 */
public class GenerateEtatExpeditionReport {

<<<<<<< HEAD
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}
	public static Date increment_decrementDays(boolean increment, Date date, int days) {
		Date newdDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (increment) {
			cal.add(Calendar.DATE, days); // minus number would decrement the
											// days
			newdDate = cal.getTime();
		} else {
			cal.add(Calendar.DATE, -days);
			newdDate = cal.getTime();
		}

		return newdDate;
	}
	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	Map<Facture, String> map = new HashMap<>();
	List<String> observations = new ArrayList<>();
	List<Date> dates = new ArrayList<>();
	double farineTotal = 0;
	double sonTotal = 0;

	double montantTotal = 0;

	double versementTotal = 0;

	double differenceTotal = 0;

	public void Facture_ClientParJour(Date jour) {
		List<Facture> list = null;
		Map<Facture, String> map = new HashMap<>();
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {

			list = new ArrayList<>();
			list = session.createQuery("from Facture where deleted='" + false + "'").list();
			Calendar cal = Calendar.getInstance();
			Date yearStart = new GregorianCalendar(cal.get(Calendar.YEAR), 0, 1).getTime();
			dates = getDaysBetweenDates(yearStart, increment_decrementDays(true, jour, 1));
			List<Facture> listFactures = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < dates.size(); j++) {
					if (dates.get(j).equals(list.get(i).getDate())) {
						listFactures.add(list.get(i));
					}
				}
			}

			for (int i = 0; i < listFactures.size(); i++) {
				if (listFactures.get(i).getDate().equals(jour)) {
					map.put(listFactures.get(i), listFactures.get(i).getClient().getName());
				}
			}

			// Total a ce jour
			for (int i = 0; i < listFactures.size(); i++) {

				List<Facture_Produit> fpList = Facture_ProduitQueries.list(listFactures.get(i));

				for (int j = 0; j < fpList.size(); j++) {

					if (fpList.get(j).getProduit().getNom().equals("FARINE 50")) {
						farineTotal = farineTotal + fpList.get(j).getQte_fact();
					} else {
						sonTotal = sonTotal + fpList.get(j).getQte_fact();
					}
				}

				// for (int j = 0; j <
				// listFactures.get(i).getClient().getPayments().size(); j++) {
				// versementTotal = versementTotal +
				// listFactures.get(i).getClient().getPayments().get(j).getMontant();
				// }
				montantTotal = montantTotal + listFactures.get(i).getMontant();

			}
			List<Payment> listPay = PaymentQueries.list();
			double total = 0;
			for (int i = 0; i < listPay.size(); i++) {
				versementTotal = versementTotal + listPay.get(i).getMontant();
				for (int j = 0; j < dates.size(); j++) {
					if (dates.get(j).equals(listPay.get(i).getDate())) {
						// versementTotal = versementTotal +
						// listPay.get(i).getMontant();
					}
				}
			}
			differenceTotal = versementTotal - montantTotal;
		} finally {
			session.close();
		}
		this.map = map;
		System.out.println("---------------map: " + map);
	}

	public void generateReport(Date jour) throws IOException, JRException {

		OperationEtatExpedition operationEtatExpedition = new OperationEtatExpedition();
		Facture_ClientParJour(jour);
		List<List<String>> expeditions = getExpedition();
		List<String> clients = getClients(expeditions);
		List<String> nums = getNums(expeditions);
		List<String> produits = getProduits(expeditions);
		List<String> qteFarins = getQteFarine(expeditions);
		List<String> qteSons = getQteSon(expeditions);
		List<String> prixs = getPrix(expeditions);
		List<String> montants = getMontant(expeditions);
		List<String> versements = getVersement(expeditions);
		List<Double> sommes = sommes(jour);
		/*
		 * System.out.println(clients); System.out.println(nums);
		 * System.out.println(produits); System.out.println(qteFarins);
		 * System.out.println(qteSons); System.out.println(prixs);
		 * System.out.println(montants); System.out.println(versements);
		 * System.out.println(sommes);
		 */
		double totalFarine = round(sommes.get(0), 2);
		double totalSon = round(sommes.get(1), 2);
		double totalMontant = round(sommes.get(2), 2);
		double totalVersement = round(sommes.get(3), 2);
		double totalVersemntMoinMontant = round(sommes.get(4), 2);
		double totalQuantite = round(sommes.get(5), 2);
		String newDate = new SimpleDateFormat("dd-MM-yyyy").format(jour);
		operationEtatExpedition.putReportInfo(newDate, String.valueOf(totalFarine), String.valueOf(totalSon),
				String.valueOf(totalMontant), String.valueOf(totalVersement), String.valueOf(totalQuantite),
				String.valueOf(totalVersemntMoinMontant), String.valueOf(round(farineTotal, 2)),
				String.valueOf(round(sonTotal, 2)), String.valueOf(round(montantTotal, 2)),
				String.valueOf(round(versementTotal, 2)), String.valueOf(round(differenceTotal, 2)), clients, nums,
				produits, qteFarins, qteSons, prixs, montants, versements, observations);
		operationEtatExpedition.printReport();

	}

	public List<String> getClients(List<List<String>> expeditions) {
		return getList(expeditions, 0);
	}

	public List<List<String>> getExpedition() {
		List<List<String>> expeditions = new ArrayList<>();
		for (Facture facture : map.keySet()) {
			List<String> expedition = new ArrayList<>();
			List<Facture_Produit> getQtes = Facture_ProduitQueries.list(facture);
			for (int i = 0; i < getQtes.size(); i++) {
				expedition.add(facture.getClient().getName());
				expedition.add(String.valueOf(facture.getIdFacture()));
				expedition.add(getQtes.get(i).getProduit().getNom().toString());
				if (getQtes.get(i).getProduit().getNom().equals("FARINE 50")) {
					expedition.add(String.valueOf(getQtes.get(i).getQte_fact()));
					expedition.add("0");
				} else {
					expedition.add("0");
					expedition.add(String.valueOf(getQtes.get(i).getQte_fact()));
				}
				expedition.add(String.valueOf(getQtes.get(i).getProduit().getPrix()));
				expedition.add(String.valueOf(facture.getMontant()));
				// check if empty
				if (facture.getClient().getPayments().isEmpty()) {
					expedition.add(String.valueOf(0));
				} else {
					expedition.add(String.valueOf(0));
					// iterate over payement checking date
					// double totalVersement = 0;
					// for (int k = 0; k <
					// facture.getClient().getPayments().size(); k++) {
					// if
					// (facture.getDate().equals(facture.getClient().getPayments().get(k).getDate()))
					// {
					// totalVersement = totalVersement +
					// facture.getClient().getPayments().get(k).getMontant();
					// }
					// }
					// expedition.add(String.valueOf(totalVersement));

				}
				observations.add("");

			}

			// System.out.println("list expidition ------------------" +
			// expedition);
			expeditions.add(expedition);
		}
		// versement par un clien
		List<Payment> listPay = PaymentQueries.list();
		double total = 0;
		for (int i = 0; i < listPay.size(); i++) {
			for (int j = 0; j < dates.size(); j++) {
				if (dates.get(j).equals(listPay.get(i).getDate())) {
					boolean found = false;
					for (Facture facture : map.keySet()) {
						if (facture.getClient().equals(listPay.get(i).getClient())) {
							found = true;
						}
					}
					if (!found) {
						List<String> expedition = new ArrayList<>();
						expedition.add(
								listPay.get(i).getClient().getName() + " " + listPay.get(i).getClient().getPrenom());
						expedition.add("");
						expedition.add("");
						expedition.add("");
						expedition.add("");
						expedition.add("");
						expedition.add("");
						expedition.add(String.valueOf(listPay.get(i).getMontant()));
						observations.add("");
						expeditions.add(expedition);

					}
				}
			}
		}
		return expeditions;
	}

	public Map<Facture, String> getFacture_ClientParJour() {
		return map;

	}

	public List<String> getList(List<List<String>> expeditions, int n) {
		List<String> List = new ArrayList<>();
		for (int i = 0; i < expeditions.size(); i++) {
			List.add(expeditions.get(i).get(n));
		}
		return List;
	}

	public List<String> getMontant(List<List<String>> expeditions) {
		return getList(expeditions, 6);
	}

	public List<String> getNums(List<List<String>> expeditions) {
		return getList(expeditions, 1);
	}

	public List<String> getPrix(List<List<String>> expeditions) {
		return getList(expeditions, 5);
	}

	public List<String> getProduits(List<List<String>> expeditions) {
		return getList(expeditions, 2);
	}

	public List<String> getQteFarine(List<List<String>> expeditions) {
		return getList(expeditions, 3);
	}

	public List<String> getQteSon(List<List<String>> expeditions) {
		return getList(expeditions, 4);
	}

	public List<String> getVersement(List<List<String>> expeditions) {
		return getList(expeditions, 7);
	}

	// somme qte farine et son
	public double sommeFarineSon(String designationProduit) {
		double total = 0;
		for (Facture facture : map.keySet()) {
			List<Facture_Produit> fpList = Facture_ProduitQueries.list(facture);

			for (int i = 0; i < fpList.size(); i++) {
				if (fpList.get(i).getProduit().getNom().equals(designationProduit)) {
					total = total + fpList.get(i).getQte_fact();
				}
			}
			// facture.getQtes();
		}
		return total;
	}

	public double sommeMontant() {
		double total = 0;
		for (Facture facture : map.keySet()) {
			total = total + facture.getMontant();
		}
		return total;
	}

	public List<Double> sommes(Date jour) {
		List<Double> listSommes = new ArrayList<>();
		// Map<Facture, Client> map = getFacture_ClientParJour();
		try {
			// somme farine
			double sommeFarine = sommeFarineSon("FARINE 50");
			listSommes.add(sommeFarine);
			// somme Son
			double sommeSon = sommeFarineSon("SON");
			listSommes.add(sommeSon);
			// somme montant
			listSommes.add(sommeMontant());
			// somme versement
			listSommes.add(sommeVersement(jour));
			// difference : versement - montant
			double difference = sommeVersement(jour) - sommeMontant();
			// somme qte son + qte Farine
			double someQte = sommeFarine + sommeSon;
			listSommes.add(someQte);
			listSommes.add(difference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSommes;
	}

	// public double sommeVersement() {
	// double total = 0;
	// for (Facture facture : map.keySet()) {
	// for (int i = 0; i < facture.getClient().getPayments().size(); i++) {
	// total = total + facture.getClient().getPayments().get(i).getMontant();
	//
	// }
	// }
	// return total;
	// }
	public double sommeVersement(Date jour) {
		List<Payment> listPayment = PaymentQueries.list();
		double total = 0;
		for (int i = 0; i < listPayment.size(); i++) {
			if (jour.equals(listPayment.get(i).getDate())) {
				total = total + listPayment.get(i).getMontant();
			}

		}
		return total;
	}
=======
    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static Date increment_decrementDays(boolean increment, Date date, int days) {
        Date newdDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (increment) {
            cal.add(Calendar.DATE, days); // minus number would decrement the
            // days
            newdDate = cal.getTime();
        } else {
            cal.add(Calendar.DATE, -days);
            newdDate = cal.getTime();
        }

        return newdDate;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    Map<Facture, String> map = new HashMap<>();
    List<String> observations = new ArrayList<>();
    List<Date> dates = new ArrayList<>();
    double farineTotal = 0;
    double sonTotal = 0;

    double montantTotal = 0;

    double versementTotal = 0;

    double differenceTotal = 0;

    public void Facture_ClientParJour(Date jour) {
        List<Facture> list = null;
        Map<Facture, String> map = new HashMap<>();
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Facture where deleted='" + false + "'").list();
            Calendar cal = Calendar.getInstance();
            Date yearStart = new GregorianCalendar(cal.get(Calendar.YEAR), 0, 1).getTime();
            dates = getDaysBetweenDates(yearStart, increment_decrementDays(true, jour, 1));
            List<Facture> listFactures = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < dates.size(); j++) {
                    if (dates.get(j).equals(list.get(i).getDate())) {
                        listFactures.add(list.get(i));
                    }
                }
            }

            for (int i = 0; i < listFactures.size(); i++) {
                if (listFactures.get(i).getDate().equals(jour)) {
                    map.put(listFactures.get(i), listFactures.get(i).getClient().getName());
                }
            }

            // Total a ce jour
            for (int i = 0; i < listFactures.size(); i++) {

                List<Facture_Produit> fpList = Facture_ProduitQueries.list(listFactures.get(i));

                for (int j = 0; j < fpList.size(); j++) {

                    if (fpList.get(j).getProduit().getNom().equals("FARINE 50")) {
                        farineTotal = farineTotal + fpList.get(j).getQte_fact();
                    } else {
                        sonTotal = sonTotal + fpList.get(j).getQte_fact();
                    }
                }

                // for (int j = 0; j <
                // listFactures.get(i).getClient().getPayments().size(); j++) {
                // versementTotal = versementTotal +
                // listFactures.get(i).getClient().getPayments().get(j).getMontant();
                // }
                montantTotal = montantTotal + listFactures.get(i).getMontant();

            }
            List<Payment> listPay = PaymentQueries.list();
            double total = 0;
            for (int i = 0; i < listPay.size(); i++) {
                //versementTotal = versementTotal + listPay.get(i).getMontant();
                for (int j = 0; j < dates.size(); j++) {
                    if (dates.get(j).equals(listPay.get(i).getDate())) {
                        versementTotal = versementTotal + listPay.get(i).getMontant();
                    }
                }
            }
            differenceTotal = versementTotal - montantTotal;
        } finally {
            session.close();
        }
        this.map = map;
        System.out.println("---------------map: " + map);
    }

    public void generateReport(Date jour) throws IOException, JRException {

        OperationEtatExpedition operationEtatExpedition = new OperationEtatExpedition();
        Facture_ClientParJour(jour);
        List<List<String>> expeditions = getExpedition(jour);
        List<String> clients = getClients(expeditions);
        List<String> nums = getNums(expeditions);
        List<String> produits = getProduits(expeditions);
        List<String> qteFarins = getQteFarine(expeditions);
        List<String> qteSons = getQteSon(expeditions);
        List<String> prixs = getPrix(expeditions);
        List<String> montants = getMontant(expeditions);
        List<String> versements = getVersement(expeditions);
        List<Double> sommes = sommes(jour);

        double totalFarine = round(sommes.get(0), 2);
        double totalSon = round(sommes.get(1), 2);
        double totalMontant = round(sommes.get(2), 2);
        double totalVersement = round(sommes.get(3), 2);
        double totalVersemntMoinMontant = round(sommes.get(4), 2);
        double totalQuantite = round(sommes.get(5), 2);
        String newDate = new SimpleDateFormat("dd-MM-yyyy").format(jour);
        operationEtatExpedition.putReportInfo(newDate, String.valueOf(totalFarine), String.valueOf(totalSon),
                String.valueOf(totalMontant), String.valueOf(totalVersement), String.valueOf(totalQuantite),
                String.valueOf(totalVersemntMoinMontant), String.valueOf(round(farineTotal, 2)),
                String.valueOf(round(sonTotal, 2)), String.valueOf(round(montantTotal, 2)),
                String.valueOf(round(versementTotal, 2)), String.valueOf(round(differenceTotal, 2)), clients, nums,
                produits, qteFarins, qteSons, prixs, montants, versements, observations);
        operationEtatExpedition.printReport();

    }

    public List<String> getClients(List<List<String>> expeditions) {
        return getList(expeditions, 0);
    }

    public List<List<String>> getExpedition(Date jour) {
        List<List<String>> expeditions = new ArrayList<>();
        for (Facture facture : map.keySet()) {
            List<String> expedition = new ArrayList<>();
            List<Facture_Produit> getQtes = Facture_ProduitQueries.list(facture);
            for (int i = 0; i < getQtes.size(); i++) {
                expedition.add(facture.getClient().getName());
                expedition.add(String.valueOf(facture.getIdFacture()));
                expedition.add(getQtes.get(i).getProduit().getNom().toString());
                if (getQtes.get(i).getProduit().getNom().equals("FARINE 50")) {
                    expedition.add(String.valueOf(getQtes.get(i).getQte_fact()));
                    expedition.add("0");
                } else {
                    expedition.add("0");
                    expedition.add(String.valueOf(getQtes.get(i).getQte_fact()));
                }
                expedition.add(String.valueOf(getQtes.get(i).getProduit().getPrix()));
                expedition.add(String.valueOf(facture.getMontant()));
                // check if empty
                if (facture.getClient().getPayments().isEmpty()) {
                    expedition.add(String.valueOf(0));
                } else {
                    expedition.add(String.valueOf(0));
                    
                }
                observations.add("");

            }

            expeditions.add(expedition);
        }
        // versement par un clien
        List<Payment> listPay = PaymentQueries.list();
        double total = 0;
        for (int i = 0; i < listPay.size(); i++) {
                if (jour.equals(listPay.get(i).getDate())) {
                    
                        List<String> expedition = new ArrayList<>();
                        expedition.add(
                                listPay.get(i).getClient().getName() + " " + listPay.get(i).getClient().getPrenom());
                        expedition.add("");
                        expedition.add("");
                        expedition.add("");
                        expedition.add("");
                        expedition.add("");
                        expedition.add("");
                        expedition.add(String.valueOf(listPay.get(i).getMontant()));
                        observations.add("");
                        expeditions.add(expedition);

                    
                
            }
        }
        return expeditions;
    }

    public Map<Facture, String> getFacture_ClientParJour() {
        return map;

    }

    public List<String> getList(List<List<String>> expeditions, int n) {
        List<String> List = new ArrayList<>();
        for (int i = 0; i < expeditions.size(); i++) {
            List.add(expeditions.get(i).get(n));
        }
        return List;
    }

    public List<String> getMontant(List<List<String>> expeditions) {
        return getList(expeditions, 6);
    }

    public List<String> getNums(List<List<String>> expeditions) {
        return getList(expeditions, 1);
    }

    public List<String> getPrix(List<List<String>> expeditions) {
        return getList(expeditions, 5);
    }

    public List<String> getProduits(List<List<String>> expeditions) {
        return getList(expeditions, 2);
    }

    public List<String> getQteFarine(List<List<String>> expeditions) {
        return getList(expeditions, 3);
    }

    public List<String> getQteSon(List<List<String>> expeditions) {
        return getList(expeditions, 4);
    }

    public List<String> getVersement(List<List<String>> expeditions) {
        return getList(expeditions, 7);
    }

    // somme qte farine et son
    public double sommeFarineSon(String designationProduit) {
        double total = 0;
        for (Facture facture : map.keySet()) {
            List<Facture_Produit> fpList = Facture_ProduitQueries.list(facture);

            for (int i = 0; i < fpList.size(); i++) {
                if (fpList.get(i).getProduit().getNom().equals(designationProduit)) {
                    total = total + fpList.get(i).getQte_fact();
                }
            }
            // facture.getQtes();
        }
        return total;
    }

    public double sommeMontant() {
        double total = 0;
        for (Facture facture : map.keySet()) {
            total = total + facture.getMontant();
        }
        return total;
    }

    public List<Double> sommes(Date jour) {
        List<Double> listSommes = new ArrayList<>();
        // Map<Facture, Client> map = getFacture_ClientParJour();
        try {
            // somme farine
            double sommeFarine = sommeFarineSon("FARINE 50");
            listSommes.add(sommeFarine);
            // somme Son
            double sommeSon = sommeFarineSon("SON");
            listSommes.add(sommeSon);
            // somme montant
            listSommes.add(sommeMontant());
            // somme versement
            listSommes.add(sommeVersement(jour));
            // difference : versement - montant
            double difference = sommeVersement(jour) - sommeMontant();
            // somme qte son + qte Farine
            double someQte = sommeFarine + sommeSon;
            listSommes.add(someQte);
            listSommes.add(difference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSommes;
    }

    // public double sommeVersement() {
    // double total = 0;
    // for (Facture facture : map.keySet()) {
    // for (int i = 0; i < facture.getClient().getPayments().size(); i++) {
    // total = total + facture.getClient().getPayments().get(i).getMontant();
    //
    // }
    // }
    // return total;
    // }
    public double sommeVersement(Date jour) {
        List<Payment> listPayment = PaymentQueries.list();
        double total = 0;
        for (int i = 0; i < listPayment.size(); i++) {
            if (jour.equals(listPayment.get(i).getDate())) {
                total = total + listPayment.get(i).getMontant();
            }

        }
        return total;
    }
>>>>>>> 48e5978c8ca1edc489606e1fad7288796221534e

}
