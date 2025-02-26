package org.example.orderService.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.orderService.domain.po.Prize;
import org.example.orderService.domain.vo.order.MultiDrawVo;
import org.example.orderService.http.HttpResult;
import org.example.orderService.service.LuckyDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/luckyDraw")
@Slf4j
public class LuckyDrawController {

    @Autowired
    LuckyDrawService luckyDrawService;

    @PostMapping("/multi")
    public HttpResult<List<Long>> multiDraw(@RequestBody MultiDrawVo listVo) {
        var prizes = luckyDrawService.multiDraw(listVo);
        var res = new ArrayList<Long>();
        for (var prize : prizes) {
            res.add(prize == null ? null : prize.getId());
        }
        return HttpResult.buildSuccess(res);
    }

}
