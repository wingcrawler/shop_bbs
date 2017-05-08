package com.sqe.shop.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sqe.shop.service.cached.CachedService;

@Component("startupListener")
public class StartupListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private CachedService cachedService;
		
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		System.out.println("spring容器初始化完毕================================================");
		cachedInit();
	}

	private void cachedInit() {
		cachedService.init();
		/*cachedService.getBundle();//国际化
		cachedService.refreshProductTypeList();//商城产品类别
		cachedService.initMap();//map缓存
*/	}

}
