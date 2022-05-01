package com.example.booking.util;

import java.util.Collection;
import java.util.Iterator;

import com.example.booking.core.EntityId;

public class CommonStringUtil {

	public static String toString(Collection<? extends EntityId> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<? extends EntityId> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next().getId());
		}
		builder.append("]");
		return builder.toString();
	}
	
}
