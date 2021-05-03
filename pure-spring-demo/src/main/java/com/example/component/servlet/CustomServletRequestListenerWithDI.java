package com.example.component.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.Locale;

@Component
public class CustomServletRequestListenerWithDI implements ServletRequestListener {

  private static final Logger logger = LoggerFactory.getLogger(CustomServletRequestListenerWithDI.class);

  private final String systemName;

  public CustomServletRequestListenerWithDI(MessageSource messageSource) { // MessageSourceをインジェクション
    this.systemName = messageSource.getMessage("system.name", null, "demo", Locale.getDefault());
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    logger.debug("{} : requestInitialized : {}", systemName, sre);
    // リクエスト開始時の処理を行う。
    // (実装は省略)
  }

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    logger.debug("{} : requestDestroyed : {}", systemName, sre);
    // リクエスト終了時の処理を行う。
    // (実装は省略)
  }

}