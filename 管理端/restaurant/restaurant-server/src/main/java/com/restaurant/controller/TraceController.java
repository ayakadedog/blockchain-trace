package com.restaurant.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.restaurant.client.ProductRecordClient;
import com.restaurant.client.TaskRecordContractClient;
import com.restaurant.client.TransportRecordContractClient;
import com.restaurant.entity.*;
import com.restaurant.result.Result;
import com.restaurant.service.*;
import com.restaurant.service.material.ProduceRecordService;
import com.restaurant.service.material.ProductService;
import com.restaurant.service.transport.TransportRecordService;
import com.restaurant.utils.getLocation;
import com.restaurant.vo.TraceVo;
import com.restaurant.vo.TransportRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dev-api/api/trace")
public class TraceController {
    @Resource
    private ProductService productService;
    @Resource
    private ProduceRecordService productRecordService;
    @Resource
    private StockService stockService;
    @Resource
    private UserService userService;
    @Resource
    private CompanyService companyService;
    @Resource
    private GoodService goodService;
    @Resource
    private TransportRecordService transportRecordService;
    @Resource
    private TaskService taskService;
    @Resource
    private TaskRecordService taskRecordService;
    @Autowired
    private ProductRecordClient productRecordClient;
    @Autowired
    private TransportRecordContractClient transportRecordContractClient;
    @Autowired
    private TaskRecordContractClient taskRecordContractClient;

    //    @PostMapping("/query")
//    public Result<TraceVo> FarmerGetRoute(@RequestParam Long traceId) {
//
//        TraceVo traceVo = new TraceVo();
//        Good good = goodService.getById(traceId);
//        traceVo.setGood(good);
//
//        LambdaQueryWrapper<TaskRecord> taskRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        taskRecordLambdaQueryWrapper.eq(TaskRecord::getTaskId, good.getTaskId());
//        taskRecordLambdaQueryWrapper.orderByAsc(TaskRecord::getRecordTime);
//        List<TaskRecord> taskRecords = taskRecordService.list(taskRecordLambdaQueryWrapper);
//        traceVo.setTaskRecord(taskRecords);
//
//        LambdaQueryWrapper<TransportRecord> transportRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        transportRecordLambdaQueryWrapper.eq(TransportRecord::getProductId, good.getProductId());
//        transportRecordLambdaQueryWrapper.orderByAsc(TransportRecord::getRecordTime);
//        List<TransportRecord> transportRecords = transportRecordService.list(transportRecordLambdaQueryWrapper);
//        traceVo.setTransportRecord(transportRecords);
//
//        LambdaQueryWrapper<ProductRecord> productRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        productRecordLambdaQueryWrapper.eq(ProductRecord::getProductId, good.getProductId());
//        productRecordLambdaQueryWrapper.orderByAsc(ProductRecord::getRecordTime);
//        List<ProductRecord> productRecords = productRecordService.list(productRecordLambdaQueryWrapper);
//        traceVo.setProductRecord(productRecords);
//
//        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        companyLambdaQueryWrapper.eq(Company::getId, userService.getById(userService.getById(good.getUserId())).getCompanyId());
//        Company company = companyService.getOne(companyLambdaQueryWrapper);
//        traceVo.setCompany(company);
//
//        return Result.success(traceVo);
//    }
    @PostMapping("/query")
    public Result<TraceVo> FarmerGetRoute(@RequestParam Long traceId) throws ContractException {
        TraceVo traceVo = new TraceVo();

        Good good = goodService.getById(traceId);
        traceVo.setGood(good);


        traceVo.setTask(taskService.getOne(new LambdaQueryWrapper<Task>()
                .eq(Task::getId, good.getId())
        ));

//        List<TaskRecord> taskRecords = taskRecordService.list(new LambdaQueryWrapper<TaskRecord>()
//                .eq(TaskRecord::getTaskId, good.getTaskId())
//                .orderByAsc(TaskRecord::getRecordTime)
//        );

        List<TaskRecord> taskRecords = taskRecordContractClient.queryTaskRecordByTaskId(good.getTaskId());
        traceVo.setTaskRecord(taskRecords);

        List<TransportRecord> transportRecords = transportRecordContractClient.queryTransportRecordContractByProductId(good.getProductId());

        List<TransportRecord> temp = transportRecordService.list(new LambdaQueryWrapper<TransportRecord>()
                .eq(TransportRecord::getProductId, good.getProductId())
                .orderByAsc(TransportRecord::getRecordTime)
        );

        traceVo.setTransportRecord(transportRecords);

        traceVo.setProduct(productService.getOne(new LambdaQueryWrapper<Product>().eq(Product::getId, good.getProductId())));
//        List<ProductRecord> productRecords = productRecordService.list(new LambdaQueryWrapper<ProductRecord>()
//                .eq(ProductRecord::getProductId, good.getProductId())
//                .orderByAsc(ProductRecord::getRecordTime)
//        );
        List<ProductRecord> productRecords = productRecordClient.queryProductRecordByProductId(good.getProductId());

        traceVo.setProductRecord(productRecords);

        User user = userService.getById(good.getUserId());
        Company company = companyService.getById(user.getCompanyId());
        traceVo.setCompany(company);

        List<TransportRecordVo> transportRecordVos = new ArrayList<>();

        if (!transportRecords.isEmpty()) {
            for (int i = 0; i < transportRecords.size(); i++) {
                TransportRecordVo transportRecordVo = new TransportRecordVo();  // 创建新对象
                BeanUtils.copyProperties(transportRecords.get(i), transportRecordVo);

                user = userService.getById(transportRecords.get(i).getUserId());
                transportRecordVo.setName(user.getName());
                transportRecordVo.setPhone(user.getPhone());
                if (transportRecords.get(i).getLocationInfo() == null) {

                    transportRecordVo.setLocationInfo(getLocation.resolveLocation(
                            new Location(transportRecords.get(i).getLng(), transportRecords.get(i).getLat())));

                    transportRecords.get(i).setLocationInfo(transportRecordVo.getLocationInfo());
                    transportRecordService.updateById(transportRecords.get(i));
                } else {
                    transportRecordVo.setLocationInfo(transportRecords.get(i).getLocationInfo());
                }

                transportRecordVos.add(transportRecordVo);
            }

        }

        traceVo.setTransportRecordVos(transportRecordVos);

        return Result.success(traceVo);
    }

}
