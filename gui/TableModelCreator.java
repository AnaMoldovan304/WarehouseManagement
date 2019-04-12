package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TableModelCreator {

	public static <T> TableModel createTableModel(Class<T> beanClass, List<T> lista) {

		try {

			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
			List<String> coloane = new ArrayList<>();
			List<Method> gettere = new ArrayList<>();

			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				String nume = pd.getName();
				if (nume.equals("class")) {
					continue;
				}
				nume = Character.toUpperCase(nume.charAt(0)) + nume.substring(1);
				String[] strings = nume.split("(?=\\p{Upper})");
				String numeColoana = "";
				for (String s1 : strings) {
					numeColoana += s1 + " ";
				}

				coloane.add(numeColoana);
				Method m = pd.getReadMethod();
				gettere.add(m);
			}

			TableModel model = new AbstractTableModel() {

				private static final long serialVersionUID = 1L;

				public String getColumnName(int coloana) {
					return coloane.get(coloana);
				}

				public int getRowCount() {
					return lista.size();
				}

				public int getColumnCount() {
					return coloane.size();
				}

				public Object getValueAt(int indexRand, int indexColoana) {
					try {
						return gettere.get(indexColoana).invoke(lista.get(indexRand));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			};

			return model;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
