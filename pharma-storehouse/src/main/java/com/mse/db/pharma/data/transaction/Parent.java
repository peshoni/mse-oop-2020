/**
 *
 */
package com.mse.db.pharma.data.transaction;

import java.util.ArrayList;
import java.util.List;

import com.mse.db.pharma.utils.AtomicIndex;
import com.mse.db.pharma.utils.TableElement;

/**
 * 
 * @param <T>
 *
 */
public class Parent<T> extends AtomicIndex implements TableElement {
	private List<T> childs = new ArrayList<>();

	public List<T> getChilds() {
		return childs;
	}

	public void setChilds(List<T> childs) {
		this.childs = childs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		childs.forEach(builder::append);
		return "Parent [childs=" + builder.toString() + "]";
	}
}
