package com.os.api;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.os.api.model.Party;
import com.os.api.model.PartyRole;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Party
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")

public class SearchParty {

	@JsonProperty("partyId")
	private String partyId = null;

	@JsonProperty("partyName")
	private String partyName = null;

	@JsonProperty("partyRole")
	private PartyRole partyRole = null;

	@JsonProperty("gleifLei")
	private String gleifLei = null;

	@JsonProperty("internalPartyId")
	private String internalPartyId = null;

	@JsonProperty("users")
	private List<String> users = null;

	public SearchParty partyId(String partyId) {
		this.partyId = partyId;
		return this;
	}

	/**
	 * The unique identifier of a party - UUID
	 * 
	 * @return partyId
	 **/
	@Schema(description = "The unique identifier of a party - UUID")

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public SearchParty partyName(String partyName) {
		this.partyName = partyName;
		return this;
	}

	/**
	 * Get partyName
	 * 
	 * @return partyName
	 **/
	@Schema(description = "")

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public SearchParty gleifLei(String gleifLei) {
		this.gleifLei = gleifLei;
		return this;
	}

	public PartyRole getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(PartyRole partyRole) {
		this.partyRole = partyRole;
	}

	/**
	 * Get gleifLei
	 * 
	 * @return gleifLei
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getGleifLei() {
		return gleifLei;
	}

	public void setGleifLei(String gleifLei) {
		this.gleifLei = gleifLei;
	}

	public SearchParty internalPartyId(String internalPartyId) {
		this.internalPartyId = internalPartyId;
		return this;
	}

	/**
	 * Get internalPartyId
	 * 
	 * @return internalPartyId
	 **/
	@Schema(description = "")

	public String getInternalPartyId() {
		return internalPartyId;
	}

	public void setInternalPartyId(String internalPartyId) {
		this.internalPartyId = internalPartyId;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public List<String> getUsers() {
		return users;
	}

	public Party toParty() {
		Party party = new Party();
		party.setGleifLei(gleifLei);
		party.setInternalPartyId(internalPartyId);
		party.setPartyId(partyId);
		party.setPartyName(partyName);
		return party;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SearchParty party = (SearchParty) o;
		return Objects.equals(this.partyId, party.partyId) && Objects.equals(this.partyName, party.partyName)
				&& Objects.equals(this.gleifLei, party.gleifLei)
				&& Objects.equals(this.internalPartyId, party.internalPartyId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(partyId, partyName, gleifLei, internalPartyId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Party {\n");

		sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
		sb.append("    partyName: ").append(toIndentedString(partyName)).append("\n");
		sb.append("    gleifLei: ").append(toIndentedString(gleifLei)).append("\n");
		sb.append("    internalPartyId: ").append(toIndentedString(internalPartyId)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
