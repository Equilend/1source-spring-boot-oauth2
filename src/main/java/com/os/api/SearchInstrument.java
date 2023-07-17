package com.os.api;

public class SearchInstrument {

	String label;
	String value;
	String id;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Instrument [label=" + label + ", value=" + value + ", id=" + id + "]";
	}

}
