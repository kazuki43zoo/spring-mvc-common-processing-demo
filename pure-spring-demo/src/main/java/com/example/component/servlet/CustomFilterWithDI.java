package com.example.component.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component // コンポーネントスキャン対象にしてDIコンテナに登録
public class CustomFilterWithDI extends GenericFilterBean { // GenericFilterBeanまたはGenericFilterBeanの子クラスを指定

  private static final Logger logger = LoggerFactory.getLogger(CustomFilterWithDI.class);

  private final String systemName;

  public CustomFilterWithDI(MessageSource messageSource) { // MessageSourceをインジェクション
    this.systemName = messageSource.getMessage("system.name", null, "demo", Locale.getDefault());
  }

  @Override
  protected void initFilterBean() throws ServletException {
    logger.debug("{} : initFilterBean", systemName);
    // 初期化処理を行う。このメソッドはアプリケーション起動時に呼び出される。
    // (実装は省略)
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    logger.debug("{} : doFilter : {} {}", systemName, request, response);
    // ここに前処理を実装する
    // (実装は省略)
    // 後続処理(次のFilter又はServlet)を呼び出したくない場合は、このタイミングでメソッドを終了(return)すればよい。

    // 後続処理(次のFilter又はServlet)を呼び出す
    filterChain.doFilter(request, response);

    // ここに後処理を実装する
    // (実装は省略)
  }

  @Override
  public void destroy() {
    logger.debug("{} : destroy", systemName);
    // アプリケーション終了時に行う処理を実装する
    // (実装は省略)
  }
}
