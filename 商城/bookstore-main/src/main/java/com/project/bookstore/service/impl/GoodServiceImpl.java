package com.project.bookstore.service.impl;

import com.project.bookstore.mapper.GoodMapper;
import com.project.bookstore.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class GoodServiceImpl  implements GoodService {


    @Resource
    private GoodMapper goodMapper;


}
