package com.os.api.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* ContractsContractIdBody
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = InternalReferenceUpdate.class, name = "InternalReferenceUpdate"),
  @JsonSubTypes.Type(value = SettlementStatusUpdate.class, name = "SettlementStatusUpdate"),
  @JsonSubTypes.Type(value = SettlementInstructionUpdate.class, name = "SettlementInstructionUpdate")
})
public interface ContractsContractIdBody {

}
