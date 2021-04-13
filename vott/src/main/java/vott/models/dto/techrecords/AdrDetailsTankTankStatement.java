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
 * AdrDetailsTankTankStatement
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-04-13T13:30:43.231Z[GMT]")
public class AdrDetailsTankTankStatement {
  @SerializedName("substancesPermitted")
  private String substancesPermitted = null;

  @SerializedName("statement")
  private String statement = null;

  @SerializedName("productListRefNo")
  private String productListRefNo = null;

  @SerializedName("productListUnNo")
  private List<String> productListUnNo = null;

  @SerializedName("productList")
  private String productList = null;

  public AdrDetailsTankTankStatement substancesPermitted(String substancesPermitted) {
    this.substancesPermitted = substancesPermitted;
    return this;
  }

   /**
   * Mandatory IF vehicleDetails.type contains the word ‘tank’ or ‘battery’ ELSE, optional. If mandatory, users must select ONE of these. Users cannot select less/more than one
   * @return substancesPermitted
  **/
    public String getSubstancesPermitted() {
    return substancesPermitted;
  }

  public void setSubstancesPermitted(String substancesPermitted) {
    this.substancesPermitted = substancesPermitted;
  }

  public AdrDetailsTankTankStatement statement(String statement) {
    this.statement = statement;
    return this;
  }

   /**
   * Optional. Applicable only IF vehicleDetails.type contains the word ‘tank’ or ‘battery’
   * @return statement
  **/
    public String getStatement() {
    return statement;
  }

  public void setStatement(String statement) {
    this.statement = statement;
  }

  public AdrDetailsTankTankStatement productListRefNo(String productListRefNo) {
    this.productListRefNo = productListRefNo;
    return this;
  }

   /**
   * Optional. Applicable only IF vehicleDetails.type contains the word ‘tank’ or ‘battery’
   * @return productListRefNo
  **/
    public String getProductListRefNo() {
    return productListRefNo;
  }

  public void setProductListRefNo(String productListRefNo) {
    this.productListRefNo = productListRefNo;
  }

  public AdrDetailsTankTankStatement productListUnNo(List<String> productListUnNo) {
    this.productListUnNo = productListUnNo;
    return this;
  }

  public AdrDetailsTankTankStatement addProductListUnNoItem(String productListUnNoItem) {
    if (this.productListUnNo == null) {
      this.productListUnNo = new ArrayList<String>();
    }
    this.productListUnNo.add(productListUnNoItem);
    return this;
  }

   /**
   * Optional. Applicable only IF vehicleDetails.type contains the word ‘tank’ or ‘battery’
   * @return productListUnNo
  **/
    public List<String> getProductListUnNo() {
    return productListUnNo;
  }

  public void setProductListUnNo(List<String> productListUnNo) {
    this.productListUnNo = productListUnNo;
  }

  public AdrDetailsTankTankStatement productList(String productList) {
    this.productList = productList;
    return this;
  }

   /**
   * Optional. Applicable only IF vehicleDetails.type contains the word ‘tank’ or ‘battery’
   * @return productList
  **/
    public String getProductList() {
    return productList;
  }

  public void setProductList(String productList) {
    this.productList = productList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdrDetailsTankTankStatement adrDetailsTankTankStatement = (AdrDetailsTankTankStatement) o;
    return Objects.equals(this.substancesPermitted, adrDetailsTankTankStatement.substancesPermitted) &&
        Objects.equals(this.statement, adrDetailsTankTankStatement.statement) &&
        Objects.equals(this.productListRefNo, adrDetailsTankTankStatement.productListRefNo) &&
        Objects.equals(this.productListUnNo, adrDetailsTankTankStatement.productListUnNo) &&
        Objects.equals(this.productList, adrDetailsTankTankStatement.productList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(substancesPermitted, statement, productListRefNo, productListUnNo, productList);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdrDetailsTankTankStatement {\n");
    
    sb.append("    substancesPermitted: ").append(toIndentedString(substancesPermitted)).append("\n");
    sb.append("    statement: ").append(toIndentedString(statement)).append("\n");
    sb.append("    productListRefNo: ").append(toIndentedString(productListRefNo)).append("\n");
    sb.append("    productListUnNo: ").append(toIndentedString(productListUnNo)).append("\n");
    sb.append("    productList: ").append(toIndentedString(productList)).append("\n");
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
