package com.mobiquity.exception;

import org.apache.commons.lang3.StringUtils;

public class APIException extends Exception {

  public APIException(String message, Exception e) {
    super(message, e);
  }

  public APIException(String message) {
    super(message);
  }
}
