package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TransactingParty
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class TransactingParty   {
  @JsonProperty("partyRole")
  private PartyRole partyRole = null;

  @JsonProperty("party")
  private Party party = null;

  public TransactingParty partyRole(PartyRole partyRole) {
    this.partyRole = partyRole;
    return this;
  }

  /**
   * Get partyRole
   * @return partyRole
   **/
  @Schema(description = "")
  
    @Valid
    public PartyRole getPartyRole() {
    return partyRole;
  }

  public void setPartyRole(PartyRole partyRole) {
    this.partyRole = partyRole;
  }

  public TransactingParty party(Party party) {
    this.party = party;
    return this;
  }

  /**
   * Get party
   * @return party
   **/
  @Schema(description = "")
  
    @Valid
    public Party getParty() {
    return party;
  }

  public void setParty(Party party) {
    this.party = party;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactingParty transactingParty = (TransactingParty) o;
    return Objects.equals(this.partyRole, transactingParty.partyRole) &&
        Objects.equals(this.party, transactingParty.party);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyRole, party);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactingParty {\n");
    
    sb.append("    partyRole: ").append(toIndentedString(partyRole)).append("\n");
    sb.append("    party: ").append(toIndentedString(party)).append("\n");
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
