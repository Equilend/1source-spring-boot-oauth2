package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VenueParty
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class VenueParty   {
  @JsonProperty("partyRole")
  private PartyRole partyRole = null;

  @JsonProperty("venuePartyId")
  private String venuePartyId = null;

  @JsonProperty("internalRef")
  private InternalReference internalRef = null;

  public VenueParty partyRole(PartyRole partyRole) {
    this.partyRole = partyRole;
    return this;
  }

  /**
   * Get partyRole
   * @return partyRole
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public PartyRole getPartyRole() {
    return partyRole;
  }

  public void setPartyRole(PartyRole partyRole) {
    this.partyRole = partyRole;
  }

  public VenueParty venuePartyId(String venuePartyId) {
    this.venuePartyId = venuePartyId;
    return this;
  }

  /**
   * Get venuePartyId
   * @return venuePartyId
   **/
  @Schema(description = "")
  
    public String getVenuePartyId() {
    return venuePartyId;
  }

  public void setVenuePartyId(String venuePartyId) {
    this.venuePartyId = venuePartyId;
  }

  public VenueParty internalRef(InternalReference internalRef) {
    this.internalRef = internalRef;
    return this;
  }

  /**
   * Get internalRef
   * @return internalRef
   **/
  @Schema(description = "")
  
    @Valid
    public InternalReference getInternalRef() {
    return internalRef;
  }

  public void setInternalRef(InternalReference internalRef) {
    this.internalRef = internalRef;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VenueParty venueParty = (VenueParty) o;
    return Objects.equals(this.partyRole, venueParty.partyRole) &&
        Objects.equals(this.venuePartyId, venueParty.venuePartyId) &&
        Objects.equals(this.internalRef, venueParty.internalRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyRole, venuePartyId, internalRef);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VenueParty {\n");
    
    sb.append("    partyRole: ").append(toIndentedString(partyRole)).append("\n");
    sb.append("    venuePartyId: ").append(toIndentedString(venuePartyId)).append("\n");
    sb.append("    internalRef: ").append(toIndentedString(internalRef)).append("\n");
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
