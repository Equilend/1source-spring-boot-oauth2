package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Venue
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Venue   {
  @JsonProperty("type")
  private VenueType type = null;

  @JsonProperty("platform")
  private Platform platform = null;

  @JsonProperty("venueParties")
  private VenueParties venueParties = null;

  public Venue type(VenueType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(description = "")
  
    @Valid
    public VenueType getType() {
    return type;
  }

  public void setType(VenueType type) {
    this.type = type;
  }

  public Venue platform(Platform platform) {
    this.platform = platform;
    return this;
  }

  /**
   * Get platform
   * @return platform
   **/
  @Schema(description = "")
  
    @Valid
    public Platform getPlatform() {
    return platform;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  }

  public Venue venueParties(VenueParties venueParties) {
    this.venueParties = venueParties;
    return this;
  }

  /**
   * Get venueParties
   * @return venueParties
   **/
  @Schema(description = "")
  
    @Valid
    public VenueParties getVenueParties() {
    return venueParties;
  }

  public void setVenueParties(VenueParties venueParties) {
    this.venueParties = venueParties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Venue venue = (Venue) o;
    return Objects.equals(this.type, venue.type) &&
        Objects.equals(this.platform, venue.platform) &&
        Objects.equals(this.venueParties, venue.venueParties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, platform, venueParties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Venue {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
    sb.append("    venueParties: ").append(toIndentedString(venueParties)).append("\n");
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
