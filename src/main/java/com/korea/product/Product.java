package com.korea.product;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="product")
public class Product {
	  @Id  // P.K 설정 (필수)  // 자동증가 
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private  Long   idx  ;
	  
	  @Column(length = 2, nullable = false)
	  private String  category  ;
	  
	  @Column(length = 10, unique = true,  nullable = false)
	  private String  pid  ;
	  
	  @Column(length = 30, nullable = false )
	  private String  pname ;
	  
	  private int pprice  ;
	  private String pdesc  ;
	  private int pquantity  ;

	  @Transient  // 업로드용 임시 필드
	  private  MultipartFile    pfile  ;
	  private  String pimgname  ;

	  private  String padmin ; // 상품관리자
	  private  String today ;  // 상품등록일
	  
	  @Transient
	  private  String ch1;
		
	  @Transient
	  private  String ch2;
}
