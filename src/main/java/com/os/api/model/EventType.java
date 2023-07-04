package com.os.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets EventType
 */
public enum EventType {
  TRADE("TRADE"),
    TRADE_CANCEL("TRADE_CANCEL"),
    CONTRACT("CONTRACT"),
    CONTRACT_UPDATE("CONTRACT_UPDATE"),
    CONTRACT_APPROVE("CONTRACT_APPROVE"),
    CONTRACT_CANCEL("CONTRACT_CANCEL"),
    CONTRACT_DECLINE("CONTRACT_DECLINE"),
    RERATE("RERATE"),
    RERATE_APPROVE("RERATE_APPROVE"),
    RERATE_CANCEL("RERATE_CANCEL"),
    RERATE_DECLINE("RERATE_DECLINE"),
    RETURN("RETURN"),
    RETURN_CANCEL("RETURN_CANCEL"),
    RECALL("RECALL"),
    RECALL_CANCEL("RECALL_CANCEL"),
    BUYIN("BUYIN"),
    BUYIN_APPROVE("BUYIN_APPROVE"),
    BUYIN_CANCEL("BUYIN_CANCEL"),
    BUYIN_COMPLETE("BUYIN_COMPLETE"),
    ALLOCATION("ALLOCATION"),
    SPLIT("SPLIT");

  private String value;

  EventType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static EventType fromValue(String text) {
    for (EventType b : EventType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
