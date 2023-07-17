package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * SettlementStatusUpdate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class SettlementStatusUpdate  implements ContractsContractIdBody {
  @JsonProperty("settlementStatus")
  private SettlementStatus settlementStatus = null;

  public SettlementStatusUpdate settlementStatus(SettlementStatus settlementStatus) {
    this.settlementStatus = settlementStatus;
    return this;
  }

  /**
   * Get settlementStatus
   * @return settlementStatus
   **/
  @Schema(description = "")
  
    @Valid
    public SettlementStatus getSettlementStatus() {
    return settlementStatus;
  }

  public void setSettlementStatus(SettlementStatus settlementStatus) {
    this.settlementStatus = settlementStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettlementStatusUpdate settlementStatusUpdate = (SettlementStatusUpdate) o;
    return Objects.equals(this.settlementStatus, settlementStatusUpdate.settlementStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(settlementStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettlementStatusUpdate {\n");
    
    sb.append("    settlementStatus: ").append(toIndentedString(settlementStatus)).append("\n");
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
