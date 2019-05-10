package com.hhf.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhf.springcloud.entities.Dept;
import com.hhf.springcloud.service.DeptService;

@RestController
public class DeptController {

	@Autowired
	private DeptService deptService;

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean addDept(@RequestBody Dept dept) {
		return deptService.addDept(dept);
	}

	@RequestMapping(value = "/dept/findById/{id}", method = RequestMethod.GET)
	public Dept findById(@PathVariable("id") Long id) {
		return deptService.findById(id);
	}

	@RequestMapping(value = "/dept/findAll", method = RequestMethod.GET)
	public List<Dept> findAll() {
		return deptService.findAll();
	}

	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery() {
		List<String> lists = client.getServices();
		System.out.println("************" + lists);
		List<ServiceInstance> arryLists = client.getInstances("microservicecloud-dept");
		for (ServiceInstance each : arryLists) {
			System.out.println(
					each.getServiceId() + "\t" + each.getHost() + "\t" + each.getPort() + "\t" + each.getUri());
		}
		return this.client;
	}

}
