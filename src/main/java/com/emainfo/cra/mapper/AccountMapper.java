package com.emainfo.cra.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.emainfo.cra.dto.AccountDto;
import com.emainfo.cra.generic.GenericMapper;
import com.emainfo.cra.model.Account;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper extends GenericMapper<Account, AccountDto> {
//Empty for now
}