package com.ahmety.nbeczzonguldak.Model;

import java.util.List;

public class RequestStatus {
    private boolean success;
    private List<Result> result;

   // @JsonProperty("success")
    public boolean getSuccess() { return success; }
  //  @JsonProperty("success")
    public void setSuccess(boolean value) { this.success = value; }

   // @JsonProperty("result")
    public List<Result> getResult() { return result; }
  //  @JsonProperty("result")
    public void setResult(List<Result> value) { this.result = value; }
}
