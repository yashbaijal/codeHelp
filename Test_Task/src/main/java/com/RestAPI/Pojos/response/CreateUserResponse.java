package com.RestAPI.Pojos.response;

import java.io.Serializable;
import java.util.List;

public class CreateUserResponse implements Serializable {

 private List<Results> results;
 private Info info;

 public List<Results> getResults() {
  return this.results;
 }

 public void setResults(List<Results> results) {
  this.results = results;
 }

 public Info getInfo() {
  return info;
 }

 public void setInfo(Info info) {
  this.info = info;
 }
}
