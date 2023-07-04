package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Settlement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Settlement   {
  @JsonProperty("partyRole")
  private PartyRole partyRole = null;

  @JsonProperty("instruction")
  private SettlementInstruction instruction = null;

  public Settlement partyRole(PartyRole partyRole) {
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

  public Settlement instruction(SettlementInstruction instruction) {
    this.instruction = instruction;
    return this;
  }

  /**
   * Get instruction
   * @return instruction
   **/
  @Schema(description = "")
  
    @Valid
    public SettlementInstruction getInstruction() {
    return instruction;
  }

  public void setInstruction(SettlementInstruction instruction) {
    this.instruction = instruction;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Settlement settlement = (Settlement) o;
    return Objects.equals(this.partyRole, settlement.partyRole) &&
        Objects.equals(this.instruction, settlement.instruction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyRole, instruction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Settlement {\n");
    
    sb.append("    partyRole: ").append(toIndentedString(partyRole)).append("\n");
    sb.append("    instruction: ").append(toIndentedString(instruction)).append("\n");
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
