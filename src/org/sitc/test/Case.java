package org.sitc.test;

public final class Case<Expected, Actual> {
	private final Expected expected;
	private final Actual actual;

	public Case(final Expected expected, final Actual actual) {
		this.expected = expected;
		this.actual = actual;
	}

	public Expected getExpected() {
		return expected;
	}

	public Actual getActual() {
		return actual;
	}
}
