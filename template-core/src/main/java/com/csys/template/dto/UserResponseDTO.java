/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.template.dto;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 *
 * @author Yosra
 */
@Data
public class UserResponseDTO {
   
  @ApiModelProperty(position = 0)
  private String username;
    
}
