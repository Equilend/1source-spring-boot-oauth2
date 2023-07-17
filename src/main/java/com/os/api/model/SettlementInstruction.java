package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SettlementInstruction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class SettlementInstruction   {
  @JsonProperty("settlementBic")
  private String settlementBic = null;

  @JsonProperty("localAgentBic")
  private String localAgentBic = null;

  @JsonProperty("localAgentName")
  private String localAgentName = null;

  @JsonProperty("localAgentAcct")
  private String localAgentAcct = null;

  @JsonProperty("localMarketFields")
  @Valid
  private List<LocalMarketField> localMarketFields = null;

  public SettlementInstruction settlementBic(String settlementBic) {
    this.settlementBic = settlementBic;
    return this;
  }

  /**
   * Business Identifier Code (BIC) used to identify Place of Settlement (PSET)
   * @return settlementBic
   **/
  @Schema(required = true, description = "Business Identifier Code (BIC) used to identify Place of Settlement (PSET)")
      @NotNull

    public String getSettlementBic() {
    return settlementBic;
  }

  public void setSettlementBic(String settlementBic) {
    this.settlementBic = settlementBic;
  }

  public SettlementInstruction localAgentBic(String localAgentBic) {
    this.localAgentBic = localAgentBic;
    return this;
  }

  /**
   * BIC used to identify local agent that will interact with PSET
   * @return localAgentBic
   **/
  @Schema(required = true, description = "BIC used to identify local agent that will interact with PSET")
      @NotNull

    public String getLocalAgentBic() {
    return localAgentBic;
  }

  public void setLocalAgentBic(String localAgentBic) {
    this.localAgentBic = localAgentBic;
  }

  public SettlementInstruction localAgentName(String localAgentName) {
    this.localAgentName = localAgentName;
    return this;
  }

  /**
   * Name of local agent that will interact with PSET
   * @return localAgentName
   **/
  @Schema(required = true, description = "Name of local agent that will interact with PSET")
      @NotNull

    public String getLocalAgentName() {
    return localAgentName;
  }

  public void setLocalAgentName(String localAgentName) {
    this.localAgentName = localAgentName;
  }

  public SettlementInstruction localAgentAcct(String localAgentAcct) {
    this.localAgentAcct = localAgentAcct;
    return this;
  }

  /**
   * Account within local agent that will interact with PSET
   * @return localAgentAcct
   **/
  @Schema(required = true, description = "Account within local agent that will interact with PSET")
      @NotNull

    public String getLocalAgentAcct() {
    return localAgentAcct;
  }

  public void setLocalAgentAcct(String localAgentAcct) {
    this.localAgentAcct = localAgentAcct;
  }

  public SettlementInstruction localMarketFields(List<LocalMarketField> localMarketFields) {
    this.localMarketFields = localMarketFields;
    return this;
  }

  public SettlementInstruction addLocalMarketFieldsItem(LocalMarketField localMarketFieldsItem) {
    if (this.localMarketFields == null) {
      this.localMarketFields = new ArrayList<LocalMarketField>();
    }
    this.localMarketFields.add(localMarketFieldsItem);
    return this;
  }

  /**
   * Get localMarketFields
   * @return localMarketFields
   **/
  @Schema(description = "")
      @Valid
    public List<LocalMarketField> getLocalMarketFields() {
    return localMarketFields;
  }

  public void setLocalMarketFields(List<LocalMarketField> localMarketFields) {
    this.localMarketFields = localMarketFields;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettlementInstruction settlementInstruction = (SettlementInstruction) o;
    return Objects.equals(this.settlementBic, settlementInstruction.settlementBic) &&
        Objects.equals(this.localAgentBic, settlementInstruction.localAgentBic) &&
        Objects.equals(this.localAgentName, settlementInstruction.localAgentName) &&
        Objects.equals(this.localAgentAcct, settlementInstruction.localAgentAcct) &&
        Objects.equals(this.localMarketFields, settlementInstruction.localMarketFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(settlementBic, localAgentBic, localAgentName, localAgentAcct, localMarketFields);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettlementInstruction {\n");
    
    sb.append("    settlementBic: ").append(toIndentedString(settlementBic)).append("\n");
    sb.append("    localAgentBic: ").append(toIndentedString(localAgentBic)).append("\n");
    sb.append("    localAgentName: ").append(toIndentedString(localAgentName)).append("\n");
    sb.append("    localAgentAcct: ").append(toIndentedString(localAgentAcct)).append("\n");
    sb.append("    localMarketFields: ").append(toIndentedString(localMarketFields)).append("\n");
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
