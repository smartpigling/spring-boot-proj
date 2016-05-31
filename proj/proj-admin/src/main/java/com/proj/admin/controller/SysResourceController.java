package com.proj.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proj.admin.domain.SysResource;
import com.proj.admin.service.SysResourceService;
import com.proj.admin.util.AjaxUtils;
import com.proj.admin.util.PageWrapper;
import com.proj.admin.util.StringUtils;

/**
 * 系统资源配置
 * 
 * @author Tang Xiaochuan
 *
 */

@Controller
public class SysResourceController {

	private static final Logger logger =LoggerFactory.getLogger(SysResourceController.class);
	
    
	@Autowired
	private SysResourceService sysResourceService;
	
	
	@RequestMapping("/resource/new")
	public String newResource(Model model){
		model.addAttribute("resource", new SysResource());
		return "resource/resourceform";
	}

	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	public String saveResource(SysResource resource){
		sysResourceService.saveResource(resource);
		return "redirect:/resource/"+resource.getResourceId();
	}
	
	@RequestMapping("/resource/{resourceId}")
	public String showResource(@PathVariable String resourceId, Model model){
		model.addAttribute("resource", sysResourceService.getResourceByResourceId(resourceId));
		return "resource/resourceshow";
	}
	
	
	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	public String listResource(@PageableDefault(sort={"priority"},direction = Direction.DESC) Pageable pageable,
			@RequestParam Map<String, Object> searchTerm,Model model){
		
        PageWrapper<SysResource> page = new PageWrapper<SysResource>(
        		sysResourceService.findResources(searchTerm, pageable),"/resources");
        
		model.addAttribute("page", page);
		model.addAttribute("searchTerm", searchTerm);
		return "resource/resources";
	}
	
	@RequestMapping("/resource/edit/{resourceId}")
	public String editResource(@PathVariable String resourceId, Model model,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
		
		model.addAttribute("resource",sysResourceService.getResourceByResourceId(resourceId));
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "resource/resourceform :: content ";
        }
		return "resource/resourceform";
	}
	
	@RequestMapping(value = "/resource/delete/{resourceId}", method = RequestMethod.GET)
	public String deleteResource(@PathVariable String resourceId){
		sysResourceService.delResourceByResourceId(resourceId);
		return "redirect:/resources";
	}	
	
	@RequestMapping(value = "/resource/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteResources(@RequestParam String ids){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			String[] resourceIds = StringUtils.convertStrToArray(ids,",");
			
			logger.info("********");
			logger.info(resourceIds.toString());
			logger.info("********");
			
			List<SysResource> resources= new ArrayList<SysResource>();
			for(String resourceId : resourceIds){
				SysResource r =new SysResource();
				r.setResourceId(resourceId);
				resources.add(r);
			}
			sysResourceService.delResources(resources);
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "failure");
			logger.error(String.format("delete resource [%s] failure message:%s!", ids, e.getMessage()));
		}
		return result;
	}
	
	
	@RequestMapping(value = "/resource/tree")
	public String getResourceTree(@RequestParam Map<String, Object> searchTerm,Model model){
		List<SysResource> resourceTree = sysResourceService.findResourceTree();
		model.addAttribute("resourceTree", resourceTree);
		return "resource/resources_tree";
		
	}
}
