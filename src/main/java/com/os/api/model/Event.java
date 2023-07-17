package com.os.api.model;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Event
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Event   {
  @JsonProperty("eventId")
  private Integer eventId = null;

  @JsonProperty("eventType")
  private EventType eventType = null;

  @JsonProperty("eventDatetime")
  private OffsetDateTime eventDatetime = null;

  @JsonProperty("resourceUri")
  private String resourceUri = null;

  public Event eventId(Integer eventId) {
    this.eventId = eventId;
    return this;
  }

  /**
   * The unique identifier of an event - UUID
   * @return eventId
   **/
  @Schema(required = true, description = "The unique identifier of an event - UUID")
      @NotNull

    public Integer getEventId() {
    return eventId;
  }

  public void setEventId(Integer eventId) {
    this.eventId = eventId;
  }

  public Event eventType(EventType eventType) {
    this.eventType = eventType;
    return this;
  }

  /**
   * Get eventType
   * @return eventType
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public Event eventDatetime(OffsetDateTime eventDatetime) {
    this.eventDatetime = eventDatetime;
    return this;
  }

  /**
   * Get eventDatetime
   * @return eventDatetime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getEventDatetime() {
    return eventDatetime;
  }

  public void setEventDatetime(OffsetDateTime eventDatetime) {
    this.eventDatetime = eventDatetime;
  }

  public Event resourceUri(String resourceUri) {
    this.resourceUri = resourceUri;
    return this;
  }

  /**
   * Get resourceUri
   * @return resourceUri
   **/
  @Schema(description = "")
  
    public String getResourceUri() {
    return resourceUri;
  }

  public void setResourceUri(String resourceUri) {
    this.resourceUri = resourceUri;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.eventId, event.eventId) &&
        Objects.equals(this.eventType, event.eventType) &&
        Objects.equals(this.eventDatetime, event.eventDatetime) &&
        Objects.equals(this.resourceUri, event.resourceUri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventId, eventType, eventDatetime, resourceUri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    eventId: ").append(toIndentedString(eventId)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    eventDatetime: ").append(toIndentedString(eventDatetime)).append("\n");
    sb.append("    resourceUri: ").append(toIndentedString(resourceUri)).append("\n");
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
