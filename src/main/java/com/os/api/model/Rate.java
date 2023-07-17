package com.os.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Rate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-05T16:14:05.744257631Z[GMT]")


public class Rate   {
  @JsonProperty("rebateBps")
  private Float rebateBps = null;

  @JsonProperty("rebateSreadBps")
  private Float rebateSreadBps = null;

  @JsonProperty("benchmarkCd")
  private BenchmarkCd benchmarkCd = null;

  @JsonProperty("feeBps")
  private Float feeBps = null;

  public Rate rebateBps(Float rebateBps) {
    this.rebateBps = rebateBps;
    return this;
  }

  /**
   * Get rebateBps
   * @return rebateBps
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Float getRebateBps() {
    return rebateBps;
  }

  public void setRebateBps(Float rebateBps) {
    this.rebateBps = rebateBps;
  }

  public Rate rebateSreadBps(Float rebateSreadBps) {
    this.rebateSreadBps = rebateSreadBps;
    return this;
  }

  /**
   * Get rebateSreadBps
   * @return rebateSreadBps
   **/
  @Schema(description = "")
  
    public Float getRebateSreadBps() {
    return rebateSreadBps;
  }

  public void setRebateSreadBps(Float rebateSreadBps) {
    this.rebateSreadBps = rebateSreadBps;
  }

  public Rate benchmarkCd(BenchmarkCd benchmarkCd) {
    this.benchmarkCd = benchmarkCd;
    return this;
  }

  /**
   * Get benchmarkCd
   * @return benchmarkCd
   **/
  @Schema(description = "")
  
    @Valid
    public BenchmarkCd getBenchmarkCd() {
    return benchmarkCd;
  }

  public void setBenchmarkCd(BenchmarkCd benchmarkCd) {
    this.benchmarkCd = benchmarkCd;
  }

  public Rate feeBps(Float feeBps) {
    this.feeBps = feeBps;
    return this;
  }

  /**
   * Get feeBps
   * @return feeBps
   **/
  @Schema(description = "")
  
    public Float getFeeBps() {
    return feeBps;
  }

  public void setFeeBps(Float feeBps) {
    this.feeBps = feeBps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rate rate = (Rate) o;
    return Objects.equals(this.rebateBps, rate.rebateBps) &&
        Objects.equals(this.rebateSreadBps, rate.rebateSreadBps) &&
        Objects.equals(this.benchmarkCd, rate.benchmarkCd) &&
        Objects.equals(this.feeBps, rate.feeBps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rebateBps, rebateSreadBps, benchmarkCd, feeBps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rate {\n");
    
    sb.append("    rebateBps: ").append(toIndentedString(rebateBps)).append("\n");
    sb.append("    rebateSreadBps: ").append(toIndentedString(rebateSreadBps)).append("\n");
    sb.append("    benchmarkCd: ").append(toIndentedString(benchmarkCd)).append("\n");
    sb.append("    feeBps: ").append(toIndentedString(feeBps)).append("\n");
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
