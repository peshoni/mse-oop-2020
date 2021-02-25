package com.mse.db.pharma.fxutils;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIndex implements TableElement {
	protected AtomicInteger index = new AtomicInteger(1);
}
