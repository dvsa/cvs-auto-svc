/*
 * Vehicles Microservice
 * This is the API spec for the vehicle microservice. Endpoints and parameters only exist for the operations getVehicle and getTechRecords. Other operations within the microservice are out of scope.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: bpecete@deloittece.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package vott.models.dto.techrecords;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AdrDetailsAdditionalNotes
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-04-13T13:30:43.231Z[GMT]")
public class AdrDetailsAdditionalNotes {
  @SerializedName("number")
  private List<String> number = null;

  @SerializedName("guidanceNotes")
  private List<String> guidanceNotes = null;

  public AdrDetailsAdditionalNotes number(List<String> number) {
    this.number = number;
    return this;
  }

  public AdrDetailsAdditionalNotes addNumberItem(String numberItem) {
    if (this.number == null) {
      this.number = new ArrayList<String>();
    }
    this.number.add(numberItem);
    return this;
  }

   /**
   * Optional for all vehicle types
   * @return number
  **/
    public List<String> getNumber() {
    return number;
  }

  public void setNumber(List<String> number) {
    this.number = number;
  }

  public AdrDetailsAdditionalNotes guidanceNotes(List<String> guidanceNotes) {
    this.guidanceNotes = guidanceNotes;
    return this;
  }

  public AdrDetailsAdditionalNotes addGuidanceNotesItem(String guidanceNotesItem) {
    if (this.guidanceNotes == null) {
      this.guidanceNotes = new ArrayList<String>();
    }
    this.guidanceNotes.add(guidanceNotesItem);
    return this;
  }

   /**
   * Optional for all vehicle types
   * @return guidanceNotes
  **/
    public List<String> getGuidanceNotes() {
    return guidanceNotes;
  }

  public void setGuidanceNotes(List<String> guidanceNotes) {
    this.guidanceNotes = guidanceNotes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdrDetailsAdditionalNotes adrDetailsAdditionalNotes = (AdrDetailsAdditionalNotes) o;
    return Objects.equals(this.number, adrDetailsAdditionalNotes.number) &&
        Objects.equals(this.guidanceNotes, adrDetailsAdditionalNotes.guidanceNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, guidanceNotes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdrDetailsAdditionalNotes {\n");
    
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    guidanceNotes: ").append(toIndentedString(guidanceNotes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
