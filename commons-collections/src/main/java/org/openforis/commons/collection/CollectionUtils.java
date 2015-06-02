/**
 * 
 */
package org.openforis.commons.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author M. Togna
 * @author S. Ricci
 * 
 */
public class CollectionUtils {

	/**
	 * 
	 * Returns an unmodifiable view of the specified list. <br/>
	 * This method makes use of the method unmodifiableList of java.util.Collections and returns an empty list if the provided list is null.
	 * 
	 * @param list
	 * @return
	 * @see java.util.Collections.unmodifiableList
	 */
	public static <T> List<T> unmodifiableList(List<? extends T> list) {
		if ( list == null ) {
			return Collections.emptyList();
		} else {
			return Collections.unmodifiableList(list);
		}
	}

	public static <T> Set<T> unmodifiableSet(Set<? extends T> set) {
		if ( set == null ) {
			return Collections.emptySet();
		} else {
			return Collections.unmodifiableSet(set);
		}
	}

	public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> map) {
		if ( map == null ) {
			return Collections.emptyMap();
		} else {
			return Collections.unmodifiableMap(map);
		}
	}
	
	public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> collection) {
		if ( collection == null ) {
			return Collections.emptyList();
		} else {
			return Collections.unmodifiableCollection(collection);
		}
	}
	
	/**
	 * Shifts the item to the specified index.
	 */
	public static <T> void shiftItem(List<T> list, T item, int toIndex) {
		int oldIndex = list.indexOf(item);
		if ( oldIndex < 0 ) {
			throw new IllegalArgumentException("Item not found");
		}
		if ( toIndex >= 0 && toIndex < list.size() ) {
			list.remove(oldIndex);
			list.add(toIndex, item);
		} else {
			throw new IndexOutOfBoundsException("Index out of bounds: " + toIndex + " (list size = " + list.size() + ")");
		}
	}
	
	public static <T, C extends Collection<T>> void filter(C collection, Predicate<T> predicate) {
	    if (collection != null && predicate != null) {
	    	Iterator<T> it = collection.iterator();
	        while(it.hasNext()) {
	            T item = it.next();
				if (predicate.evaluate(item) == false) {
	                it.remove();
	            }
	        }
	    }
	}

}
