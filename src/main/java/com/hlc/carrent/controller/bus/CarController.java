package com.hlc.carrent.controller.bus;

import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Car;
import com.hlc.carrent.service.CarService;
import com.hlc.carrent.utils.AppFileUtils;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.utils.ResultObj;
import com.hlc.carrent.vo.CarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * 车辆管理控制器
 */
@Controller
@RequestMapping("car")
public class CarController {


    @Autowired
    private CarService carService;

    /**
     * 车辆列表返回DataGridView
     */
    @ResponseBody
    @RequestMapping("loadAllCar")
    public DataGridView loadAllCar(CarVo carVo) {

        List<Car> list = carService.selectAllCar(carVo);
        System.out.println(carVo);
        int count = carService.selectAllCarCount();

        DataGridView dataGridView = new DataGridView();
        dataGridView.setCount((long) count);
        dataGridView.setData(list);
        return dataGridView;
    }
    /**
     * 添加车辆
     */
    @ResponseBody
    @RequestMapping("addCar")
    public ResultObj addCar(CarVo carVo) {
        try {
            carVo.setCreatetime(new Date());   //设置时间
            //如果不是默认图片就去掉图片的_temp的后缀
            if(!carVo.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)){
                String filePath = AppFileUtils.updateFileName(carVo.getCarimg(), SysConstast.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            carService.addCar(carVo);
            return ResultObj.ADD_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改车辆
     */
    @ResponseBody
    @RequestMapping("updateCar")
    public ResultObj updateCar(CarVo carVo) {
        try {
            String carimg = carVo.getCarimg();
            if(carimg.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
                String filePath=AppFileUtils.updateFileName(carVo.getCarimg(),SysConstast.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
                //把原来图片的删除(从数据库中获取的图片路径)
                Car car=this.carService.selectCarByCarNumber(carVo.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());
            }
            carVo.setCreatetime(new Date());   //设置时间
            this.carService.updateCar(carVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据carnumber删除车辆
     * @param carVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteCar")
    public ResultObj deleteCar(CarVo carVo) {
        try {
            carService.deleteCar(carVo.getCarnumber());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除车辆
     * @param carVo
     * @return
     */
    @ResponseBody
    @PostMapping("deleteBatchCar")
    public ResultObj deleteBatchCar(CarVo carVo) {
        try {
            carService.deleteBatchCar(carVo.getIds());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }



}
