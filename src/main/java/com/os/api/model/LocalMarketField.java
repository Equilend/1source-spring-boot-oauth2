package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * LocalMarketField
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class LocalMarketField   {
  @JsonProperty("localFieldName")
  private String localFieldName = null;

  @JsonProperty("localFieldValue")
  private String localFieldValue = null;

  public LocalMarketField localFieldName(String localFieldName) {
    this.localFieldName = localFieldName;
    return this;
  }

  /**
   * Local market field Name
   * @return localFieldName
   **/
  @Schema(description = "Local market field Name")
  
    public String getLocalFieldName() {
    return localFieldName;
  }

  public void setLocalFieldName(String localFieldName) {
    this.localFieldName = localFieldName;
  }

  public LocalMarketField localFieldValue(String localFieldValue) {
    this.localFieldValue = localFieldValue;
    return this;
  }

  /**
   * Local market field value
   * @return localFieldValue
   **/
  @Schema(description = "Local market field value")
  
    public String getLocalFieldValue() {
    return localFieldValue;
  }

  public void setLocalFieldValue(String localFieldValue) {
    this.localFieldValue = localFieldValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocalMarketField localMarketField = (LocalMarketField) o;
    return Objects.equals(this.localFieldName, localMarketField.localFieldName) &&
        Objects.equals(this.localFieldValue, localMarketField.localFieldValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localFieldName, localFieldValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocalMarketField {\n");
    
    sb.append("    localFieldName: ").append(toIndentedString(localFieldName)).append("\n");
    sb.append("    localFieldValue: ").append(toIndentedString(localFieldValue)).append("\n");
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
