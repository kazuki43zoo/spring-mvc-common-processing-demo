package com.example.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import com.example.component.servlet.CustomFilter;
import com.example.component.servlet.CustomServletRequestListener;
import com.example.component.servlet.CustomServletRequestListenerWithDI;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{AppConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{WebMvcConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected String getServletName() {
    return "app";
  }

  @Override
  protected Filter[] getServletFilters() {
    return new Filter[]{new CustomFilter()
        , new DelegatingFilterProxy("customFilterWithDI")}; // 登録したいサーブレットフィルタを返却する
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    // ルートのアプリケーションコンテキストを生成・初期化はSpring提供の機能(ContextLoader)を利用して自前で行う
    WebApplicationContext wac = createRootApplicationContext();
    ContextLoader loader = new ContextLoader(wac);
    loader.setContextInitializers(getRootApplicationContextInitializers());
    loader.initWebApplicationContext(servletContext);
    // アプリケーションコンテキストの破棄処理はリスナー(ContextLoaderListener)の実装を利用
    servletContext.addListener(new ContextLoaderListener(wac) {
      @Override
      public void contextInitialized(ServletContextEvent event) {
        // 初期化済みのため、リスナー経由で再度初期化が行われないようにする
      }
    });
    servletContext.addListener(CustomServletRequestListener.class);
    // DIコンテナからDIコンテナから取得したリスナーをサーブレットコンテナに登録
    servletContext.addListener(wac.getBean(CustomServletRequestListenerWithDI.class));

    // 親クラスのメソッドを呼び出してDispatcherServletを登録
    super.registerDispatcherServlet(servletContext);
  }

}