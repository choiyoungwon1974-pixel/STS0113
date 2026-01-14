package com.maju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.korea.product.Product;
import com.korea.product.ProductRepository;
import com.korea.product.S3UploaderService;

@RequestMapping("/product/")
@Controller
public class ProductController {

	@Autowired
	private  ProductRepository productRepo;
	
	@Autowired
	private S3UploaderService  s3UploaderService;
	
	@GetMapping("form.do")
	String main() {
	   System.out.println("==>/product/form.do");
	return "product/form";
	}
	
	@PostMapping("formOK.do")
	String formOK(Product p) {
	   System.out.println("==>/product/formOK.do");
	   
	   Long maxIdx = productRepo.findMaxIdx();
	   p.setPid(p.getCategory() + maxIdx);
	   
	   // S3 파일 업로드
	   String fileName = s3UploaderService.uploadFile(p.getPfile());
	   p.setPimgname(fileName);
	   
	   productRepo.save(p);
	   
	   return "redirect:/product/list.do";
	}
	
	@GetMapping("list.do")
	String list(Model model ) {
	   System.out.println("==>/product/list.do");
	   model.addAttribute("li", productRepo.findAllByOrderByIdxDesc());
	return "product/list";
	}
	
}
