package com.example.component.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class CustomFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    logger.debug("init : {}", filterConfig);
    // 初期化処理を行う。このメソッドはアプリケーション起動時に呼び出される。
    // サーブレットフィルタの初期化パラメータは引数のFilterConfigから取得できる。
    // (実装は省略)
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    logger.debug("doFilter : {} {}", request, response);
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
    logger.debug("destroy");
    // アプリケーション終了時に行う処理を実装する
    // (実装は省略)
  }

}
