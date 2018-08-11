package cn.devmgr.springcloud;

import org.springframework.cloud.openfeign.FeignClient;

import cn.devmgr.springcloud.shared.SampleController;

@FeignClient("sample-service")
public interface ClientController extends SampleController {

}
