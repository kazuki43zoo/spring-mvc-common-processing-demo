package com.example.component.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;
import java.util.UUID;

@RestControllerAdvice // クラスレベルに@RestControllerAdvice(or @ControllerAdvice)を指定する
public class CustomControllerAdvice {

  private static final Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);

  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    logger.debug("initBinder : {}", dataBinder);
    // WebDataBinderのメソッドを呼び出してカスタマイズする
    dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }

  @ModelAttribute("trackingId")
  public String addOneObject(@RequestHeader("X-Tracking-Id") Optional<String> trackingId) {
    logger.debug("addOneObject : {}", trackingId);
    // Modelに追加するオブジェクトを返却する
    return trackingId.orElse(UUID.randomUUID().toString());
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleSystemException(Exception e) {
    // 例外ハンドリングを行う
    logger.error("System Error occurred.", e);
    return "System Error!!";
  }

}
