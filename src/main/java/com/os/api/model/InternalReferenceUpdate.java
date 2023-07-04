package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InternalReferenceUpdate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class InternalReferenceUpdate  implements ContractsContractIdBody {
  @JsonProperty("internalRef")
  private InternalReference internalRef = null;

  public InternalReferenceUpdate internalRef(InternalReference internalRef) {
    this.internalRef = internalRef;
    return this;
  }

  /**
   * Get internalRef
   * @return internalRef
   **/
  @Schema(required = true, description = "")
      @NotNull

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
    InternalReferenceUpdate internalReferenceUpdate = (InternalReferenceUpdate) o;
    return Objects.equals(this.internalRef, internalReferenceUpdate.internalRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(internalRef);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InternalReferenceUpdate {\n");
    
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
