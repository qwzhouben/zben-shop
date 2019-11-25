
package com.zben.shop.member.entity;

import com.zben.shop.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserEntity extends BaseEntity {

	private String username;
	private String password;
	private String phone;
	private String email;

}
