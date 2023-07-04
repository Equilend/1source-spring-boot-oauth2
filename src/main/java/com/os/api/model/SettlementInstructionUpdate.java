package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SettlementInstructionUpdate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class SettlementInstructionUpdate  implements ContractsContractIdBody {
  @JsonProperty("settlement")
  private Settlement settlement = null;

  public SettlementInstructionUpdate settlement(Settlement settlement) {
    this.settlement = settlement;
    return this;
  }

  /**
   * Get settlement
   * @return settlement
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Settlement getSettlement() {
    return settlement;
  }

  public void setSettlement(Settlement settlement) {
    this.settlement = settlement;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettlementInstructionUpdate settlementInstructionUpdate = (SettlementInstructionUpdate) o;
    return Objects.equals(this.settlement, settlementInstructionUpdate.settlement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(settlement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettlementInstructionUpdate {\n");
    
    sb.append("    settlement: ").append(toIndentedString(settlement)).append("\n");
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
