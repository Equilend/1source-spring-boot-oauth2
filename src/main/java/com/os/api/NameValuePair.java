package com.os.api;

import java.util.Objects;

public class NameValuePair {

	String name;
	String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NameValuePair other = (NameValuePair) obj;
		return Objects.equals(name, other.name) && Objects.equals(value, other.value);
	}

}
