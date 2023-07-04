package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * A reference object for agreements
 */
@Schema(description = "A reference object for agreements")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class AgreementReference   {
  @JsonProperty("agreementId")
  private String agreementId = null;

  public AgreementReference agreementId(String agreementId) {
    this.agreementId = agreementId;
    return this;
  }

  /**
   * The unique identifier of a trade agreement - UUID
   * @return agreementId
   **/
  @Schema(description = "The unique identifier of a trade agreement - UUID")
  
    public String getAgreementId() {
    return agreementId;
  }

  public void setAgreementId(String agreementId) {
    this.agreementId = agreementId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AgreementReference agreementReference = (AgreementReference) o;
    return Objects.equals(this.agreementId, agreementReference.agreementId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agreementId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AgreementReference {\n");
    
    sb.append("    agreementId: ").append(toIndentedString(agreementId)).append("\n");
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
