package com.example.component.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomServletRequestListener implements ServletRequestListener {

  private static final Logger logger = LoggerFactory.getLogger(CustomServletRequestListener.class);

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    logger.debug("requestInitialized : {}", sre);
    // リクエスト開始時の処理を行う。
    // (実装は省略)
  }

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    logger.debug("requestDestroyed : {}", sre);
    // リクエスト終了時の処理を行う。
    // (実装は省略)
  }

}