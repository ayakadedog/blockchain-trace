package com.restaurant.controller.transport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.dto.TransportDto;
import com.restaurant.entity.*;
import com.restaurant.result.Result;
import com.restaurant.service.*;
import com.restaurant.service.material.MaterialService;
import com.restaurant.service.material.ProductService;
import com.restaurant.service.transport.TransportRecordService;
import com.restaurant.service.transport.TransportService;
import com.restaurant.utils.getLocation;
import com.restaurant.vo.transport.*;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.restaurant.constant.UserConstant.FARMER;
import static com.restaurant.constant.UserConstant.RAW_MATERIAL_FACTORY;

@Slf4j
@RestController
@RequestMapping("/dev-api/api/transport")
public class TransportController {

    @Resource
    private UserService userService;

    @Resource
    private TransportService transportService;

    @Resource
    private TransportRecordService transportRecordService;

    @Resource
    private ProductService productService;

    @Resource
    private MaterialService materialService;

    @Resource
    private StockService stockService;

    @Resource
    private CompanyService companyService;

    /**
     * 通知运输
     *
     * @param
     * @return
     */
    @PostMapping("/notice")
    public Result noticeTransport(@RequestBody TransportAndRecordAddVo transportRecordAddVo, HttpServletRequest request) {
        Transport transport = new Transport();
        BeanUtils.copyProperties(transportRecordAddVo, transport);
        User user = (User) request.getSession().getAttribute("user");
        transport.setName(user.getName());
        transport.setFarmerId(user.getId());
        transportService.addNoticeTransport(transport);
        return Result.success();
    }



    /**
     * 原料厂通知运输
     *
     * @param
     * @return
     */
    @PostMapping("/company/notice")
    public Result noticeTransportCompany(@RequestBody TransportAndRecordAddVo transportRecordAddVo, HttpServletRequest request) {
        Transport transport = new Transport();
        BeanUtils.copyProperties(transportRecordAddVo, transport);
        User user = (User) request.getSession().getAttribute("user");
        transport.setName(user.getName());
        transport.setFarmerId(user.getId());
        transportService.noticeTransportCompany(transport);

        return Result.success();
    }

    /**
     * 添加运输记录
     *
     * @param
     * @return
     */
    @PostMapping("/record")
    public Result addTransportRecord(@RequestBody TransportAndRecordAddVo transportRecordAddVo, HttpServletRequest request) {
        Transport transport = new Transport();
        BeanUtils.copyProperties(transportRecordAddVo, transport);
        transport.setFarmerId(transport.getFarmerId());
        transportService.addNoticeTransport(transport);
        return Result.success();
    }

    /**
     * 运输员运输工作展示
     * @param transportQueryVo
     * @param request
     * @return
     */
    @PostMapping("/page")
    public Result transportPage(@RequestBody TransportQueryVo transportQueryVo, HttpServletRequest request) {
        Long id = transportQueryVo.getId();
        Integer status = transportQueryVo.getStatus();
        Integer page = transportQueryVo.getPage();
        Integer limit = transportQueryVo.getLimit();

        Page<Transport> pageInfo = new Page(page, limit);
        Page<TransportDto> transportDtoPage = new Page<>();

        User user = (User) request.getSession().getAttribute("user");
        if (user.getId() == null) {
            return Result.error("请先登录");
        }

        LambdaQueryWrapper<Transport> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Transport::getUserId, user.getId());
        queryWrapper.eq(id != null, Transport::getProductId, id);
        queryWrapper.eq(status != null, Transport::getStatus, status);
        queryWrapper.orderByDesc(Transport::getRecordTime);

        transportService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo, transportDtoPage);

        return Result.success(transportDtoPage);
    }

    /**
     * 农户查看运输信息
     * @param productId
     * @param request
     * @return
     */
    @PostMapping("/farmer/route")
    public Result<TransportVo> FarmerGetRoute(@RequestParam Long productId, HttpServletRequest request) throws ContractException {

        User user = (User) request.getSession().getAttribute("user");

        Transport transport = transportService.getByProductId(productId);
        TransportVo route = transportRecordService.getRoute(transport, user, productId);

        return Result.success(route);
    }

    /**
     * 运输员查看运输信息
     * @param transportId
     * @return
     */
    @PostMapping("/transport/route")
    public Result<TransportVo> TransportGetRoute(@RequestParam Long transportId) throws ContractException {

        TransportVo route = transportRecordService.getRoute2(transportId);

        return Result.success(route);
    }

    /**
     * 运输员跟新运输状态
     * @param transportStatus
     * @return
     */
    @PostMapping("/status")
    public Result<Void> changeStatus(@RequestBody TransportStatus transportStatus,HttpServletRequest request) {


        Transport transport = transportService.getById(transportStatus.getTransportId());

        // 构建查询条件
        QueryWrapper<Transport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", transportStatus.getTransportId());

        // 设置更新的字段值
        transport.setStatus(transportStatus.getStatus());

        // 执行更新操作
        transportService.update(transport, queryWrapper);

        if (transportStatus.getStatus() == 2) {
            User user1 = (User)request.getSession().getAttribute("user");

            User user = userService.getById(transport.getFarmerId());

            Long companyId = transport.getCompanyId();

            if (user1.getId()==null){
                return Result.error("请先登录");
            }
//            if (Objects.equals(user.getLevel(), FARMER)) {
//                Material material = new Material();
//                Product product = productService.getById(transport.getProductId());
//                BeanUtils.copyProperties(product,material);
//                material.setCompanyId(user.getCompanyId());
//                material.setArrivalDate(LocalDateTime.now());
//                material.setStatus(1);
//                materialService.save(material);
//            }
//
//            if (Objects.equals(user.getLevel(), RAW_MATERIAL_FACTORY)) {
//                Stock stock = new Stock();
//                Material material = materialService.getById(transport.getProductId());
//                BeanUtils.copyProperties(material,stock);
//                stock.setCompanyName(companyService.getById(user.getCompanyId()).getName());
//                stock.setArrivalDate(LocalDateTime.now());
//                stock.setStatus(0);
//                stockService.save(stock);
//            }
            if (companyId == null) {
                Material material = new Material();
                Product product = productService.getById(transport.getProductId());
                BeanUtils.copyProperties(product,material);
                material.setCompanyId(user.getCompanyId());
                material.setArrivalDate(LocalDateTime.now());
                material.setStatus(1);
                materialService.save(material);
            }

            if (companyId != null) {
                Stock stock = new Stock();
                Material material = materialService.getById(transport.getProductId());
                BeanUtils.copyProperties(material,stock);
                stock.setCompanyName(companyService.getById(user.getCompanyId()).getName());
                stock.setCompanyId(companyId);
                stock.setArrivalDate(LocalDateTime.now());
                stock.setStatus(0);
                stockService.save(stock);
            }
        }


        return Result.success();
    }

    /**
     * 运输员中途定位
     * @param transportLocation
     * @return
     */
    @PostMapping("/location")
    public Result<Void> TransportSetLocation(@RequestBody TransportLocation transportLocation) {

        Transport transport = transportService.getById(transportLocation.getTransportId());
        transport.setLng(transportLocation.getLng());
        transport.setLat(transportLocation.getLat());

        transportRecordService.add(transport);

        return Result.success();
    }

}
