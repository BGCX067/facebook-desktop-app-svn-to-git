package de.lebk.facebook;

import java.lang.reflect.Field;
import java.util.List;

import javax.management.RuntimeErrorException;

public class ListUtil {
	//static calls only
	private ListUtil() {
	}
	/**
	 * Helfermethode, welche ein bestimmtes Element, mit angebenen Attributwert, heraussucht.
	 * Diese Methode gibt das erste Element zurück, was als positiv identifiziert wird.
	 * 
	 * @param list Die zu durchsuchende Liste
	 * @param attribName Der Name des attributes innherhalb des Objektes
	 * @param attribValue Der Wert des Objektes, welcher einzigartig sein sollte.
	 */
	public static <E, T> E findListEntry(List<E> list, String attribName, T attribValue){
		E result = list.get(0);
		try {
			Field field = result.getClass().getDeclaredField(attribName);
			field.setAccessible(true);
			Object value = field.get(result);
			if(!value.equals(attribValue)){
				list.remove(result);
				return findListEntry(list, attribName, attribValue);
			}
			return result;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			System.out.println("Theres no field like given");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		throw new RuntimeErrorException(new Error("FieldValue/ Fieldname not found!"));
	}
	
}
