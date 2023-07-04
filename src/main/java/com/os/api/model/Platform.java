package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Platform
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Platform   {
  @JsonProperty("gleifLei")
  private String gleifLei = null;

  @JsonProperty("legalName")
  private String legalName = null;

  @JsonProperty("mic")
  private String mic = null;

  @JsonProperty("venueName")
  private String venueName = null;

  @JsonProperty("venueRefId")
  private String venueRefId = null;

  @JsonProperty("transactionDatetime")
  private OffsetDateTime transactionDatetime = null;

  public Platform gleifLei(String gleifLei) {
    this.gleifLei = gleifLei;
    return this;
  }

  /**
   * Get gleifLei
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

  public Platform legalName(String legalName) {
    this.legalName = legalName;
    return this;
  }

  /**
   * Get legalName
   * @return legalName
   **/
  @Schema(description = "")
  
    public String getLegalName() {
    return legalName;
  }

  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }

  public Platform mic(String mic) {
    this.mic = mic;
    return this;
  }

  /**
   * Get mic
   * @return mic
   **/
  @Schema(description = "")
  
    public String getMic() {
    return mic;
  }

  public void setMic(String mic) {
    this.mic = mic;
  }

  public Platform venueName(String venueName) {
    this.venueName = venueName;
    return this;
  }

  /**
   * Get venueName
   * @return venueName
   **/
  @Schema(description = "")
  
    public String getVenueName() {
    return venueName;
  }

  public void setVenueName(String venueName) {
    this.venueName = venueName;
  }

  public Platform venueRefId(String venueRefId) {
    this.venueRefId = venueRefId;
    return this;
  }

  /**
   * Get venueRefId
   * @return venueRefId
   **/
  @Schema(description = "")
  
    public String getVenueRefId() {
    return venueRefId;
  }

  public void setVenueRefId(String venueRefId) {
    this.venueRefId = venueRefId;
  }

  public Platform transactionDatetime(OffsetDateTime transactionDatetime) {
    this.transactionDatetime = transactionDatetime;
    return this;
  }

  /**
   * Get transactionDatetime
   * @return transactionDatetime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getTransactionDatetime() {
    return transactionDatetime;
  }

  public void setTransactionDatetime(OffsetDateTime transactionDatetime) {
    this.transactionDatetime = transactionDatetime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Platform platform = (Platform) o;
    return Objects.equals(this.gleifLei, platform.gleifLei) &&
        Objects.equals(this.legalName, platform.legalName) &&
        Objects.equals(this.mic, platform.mic) &&
        Objects.equals(this.venueName, platform.venueName) &&
        Objects.equals(this.venueRefId, platform.venueRefId) &&
        Objects.equals(this.transactionDatetime, platform.transactionDatetime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gleifLei, legalName, mic, venueName, venueRefId, transactionDatetime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Platform {\n");
    
    sb.append("    gleifLei: ").append(toIndentedString(gleifLei)).append("\n");
    sb.append("    legalName: ").append(toIndentedString(legalName)).append("\n");
    sb.append("    mic: ").append(toIndentedString(mic)).append("\n");
    sb.append("    venueName: ").append(toIndentedString(venueName)).append("\n");
    sb.append("    venueRefId: ").append(toIndentedString(venueRefId)).append("\n");
    sb.append("    transactionDatetime: ").append(toIndentedString(transactionDatetime)).append("\n");
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
