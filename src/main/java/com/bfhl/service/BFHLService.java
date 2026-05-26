package com.bfhl.service;

import com.bfhl.dto.RequestDTO;
import com.bfhl.dto.ResponseDTO;

public interface BFHLService {

    ResponseDTO process(RequestDTO requestDTO);
}
