package com.mse.db.pharma.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIndex implements TableElement {
	protected AtomicInteger index = new AtomicInteger(1);
}
