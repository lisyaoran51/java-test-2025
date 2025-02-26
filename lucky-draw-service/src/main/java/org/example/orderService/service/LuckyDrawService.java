package org.example.orderService.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.orderService.domain.po.Prize;
import org.example.orderService.domain.vo.order.*;

import java.util.List;

/**
 * @author josh_sinrui
 */
public interface LuckyDrawService extends IService<Prize> {

    List<Prize> multiDraw(MultiDrawVo multiDrawVo);

    Prize oneDraw();
}