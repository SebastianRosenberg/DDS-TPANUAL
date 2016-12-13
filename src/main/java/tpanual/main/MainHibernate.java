package tpanual.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.rubro.RubroFWFactory;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;
import tpanual.utilitarios.HibernateFactorySessions;

public class MainHibernate {

	private static final String FILENAME1 = "C:\\Users\\dipatata\\Desktop\\logsincro1.log";
	private static final String FILENAME2 = "C:\\Users\\dipatata\\Desktop\\logsincro2.log";

	public static void main(String[] args) {

		BufferedReader br1 = null;
		FileReader fr1 = null;
		BufferedReader br2 = null;
		FileReader fr2 = null;
		try {

			fr1 = new FileReader(FILENAME1);
			br1 = new BufferedReader(fr1);

			String sCurrentLine1;

			br1 = new BufferedReader(new FileReader(FILENAME1));

			sCurrentLine1 = br1.readLine();

			fr2 = new FileReader(FILENAME2);
			br2 = new BufferedReader(fr2);

			String sCurrentLine2;

			br2 = new BufferedReader(new FileReader(FILENAME2));

			sCurrentLine2 = br2.readLine();			
			
			String[] strings1 = sCurrentLine1.split(", ");
			String[] strings2 = sCurrentLine2.split(", ");
			
			System.out.println("d");
			
			for (int i=0;i<strings1.length;i++){
				for (int j=0;j<strings2.length;j++){
					if (Integer.parseInt(strings1[i]) == Integer.parseInt(strings2[j])){
						System.out.println("Son iguales: " + strings1[i] + " y " + strings2[j]);
					}
				}
			}
			

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br1 != null)
					br1.close();

				if (fr1 != null)
					fr1.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
}
