package cn.devmgr.springcloud;

import org.springframework.cloud.openfeign.FeignClient;

import cn.devmgr.springcloud.common.TvSeriesController;

@FeignClient("sample-service")
public interface ClientController extends TvSeriesController {

}
