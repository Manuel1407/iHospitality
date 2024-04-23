package com.ikechukwu.ihospitality.service.impl;

import com.ikechukwu.ihospitality.model.Staff;
import com.ikechukwu.ihospitality.repository.StaffRepository;
import com.ikechukwu.ihospitality.security.StaffDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException(uuid + " not found"));

        return new StaffDetails(staff);
    }



//    @Override
//    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
//        return staffRepository.findByUuid(uuid)
//                .orElseThrow(() -> new UsernameNotFoundException(uuid + " not found"));
//    }
}
