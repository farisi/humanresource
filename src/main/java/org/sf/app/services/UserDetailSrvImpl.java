package org.sf.app.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.sf.app.DTO.UserDetailDTO;
import org.sf.app.entities.User;
import org.sf.app.entities.UserDetail;
import org.sf.app.helpers.UserDetailMapper;
import org.sf.app.repositories.UserDetailRepository;
import org.sf.app.requesters.users.UserStore;
import org.sf.app.requesters.users.UserUpdating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailSrvImpl implements UserDetailService {
	
	@Autowired
	UserDetailRepository usrDtlRepo;
	
	@Transactional
	@Override
	public UserDetail save(UserStore usrStr) {
		// TODO Auto-generated method stub
		
		UserDetail uDtl=new UserDetail();
		User u = new User();

		u.setEmail(usrStr.getEmail());
		u.setPassword(usrStr.getPassword());
		u.setCreatedAt(LocalDateTime.now());
		u.setUpdatedAt(LocalDateTime.now());
		
		uDtl.setFirstName(usrStr.getFirstName());
		uDtl.setLastName(usrStr.getLastName());
		uDtl.setAddress(usrStr.getAddress());
		uDtl.setJoinDate(usrStr.getJoinDate());
		uDtl.setBirthDate(usrStr.getBirthDate());
		uDtl.setPlaceOfBirth(usrStr.getPlaceOfBirth());
		uDtl.setMobile(usrStr.getMobile());
		uDtl.setCreatedAt(LocalDateTime.now());
		uDtl.setUpdatedAt(LocalDateTime.now());
		uDtl.setUser(u);
		
		return usrDtlRepo.save(uDtl);
	}
	


	@Override
	public UserDetail update(UserUpdating usr) {
		// TODO Auto-generated method stub
		UserDetail uDtl = usrDtlRepo.findById(usr.getId()).orElseThrow(()->new ResourceNotFoundException());
		User u = uDtl.getUser();
		u.setEmail(usr.getEmail());
		u.setUpdatedAt(LocalDateTime.now());
		uDtl.setFirstName(usr.getFirstName());
		uDtl.setLastName(usr.getLastName());
		uDtl.setAddress(usr.getAddress());
		uDtl.setJoinDate(usr.getJoinDate());
		uDtl.setBirthDate(usr.getBirthDate());
		uDtl.setPlaceOfBirth(usr.getPlaceOfBirth());
		uDtl.setMobile(usr.getMobile());
		uDtl.setUser(u);
		
		return uDtl;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<UserDetailDTO> all(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<UserDetailDTO> udList = usrDtlRepo.all(pageable);
		
		return udList;
	}

	@Override
	public Optional<UserDetail> findById(Integer id) {
		// TODO Auto-generated method stub
		return usrDtlRepo.findById(id);
	}

	@Override
	public void delete(UserDetail usrDetail) {
		// TODO Auto-generated method stub
		usrDtlRepo.delete(usrDetail);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<UserDetailDTO> findByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return usrDtlRepo.findByKeyword(keyword,pageable);
	}
}
